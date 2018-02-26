package com.online.college.service.web.auth;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.SessionContext;
import com.online.college.common.web.auth.SessionUser;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;

/**
 *
 * @Description: shiro实现用户登录，各个模块表不一样，各自处理
 * @author cmazxiaoma
 * @date 2018-02-08 10:54
 * @version V1.0
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IAuthUserService authUserService;

    /**
     * 用户的授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthenticationException("PrincipalCollection method argument cannot be null");
        }
        // 获取当前登录用户
        SessionUser user = SessionContext.getAuthUser();

        if (user == null) {
            return null;
        }
        // 设置权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户权限并且设置权限，供shiro框架使用
        info.setStringPermissions(user.getPermissions());
        return info;
    }

    /**
     * 用户登录时的认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        AuthUser authUser = null;

        try {
            AuthUser tmpAuthUser = new AuthUser();
            tmpAuthUser.setUsername(username);
            tmpAuthUser.setPassword(password);

            tmpAuthUser = this.authUserService.getByUsernameAndPassword(tmpAuthUser);

            if (null != tmpAuthUser) {
                authUser = new AuthUser();
                authUser.setId(tmpAuthUser.getId());
                authUser.setRealname(tmpAuthUser.getRealname());
                authUser.setUsername(tmpAuthUser.getUsername());
                authUser.setStatus(tmpAuthUser.getStatus());

                if (!StringUtils.isBlank(tmpAuthUser.getHeader())) {
                    authUser.setHeader(QiniuStorage.getUrl(tmpAuthUser.getHeader(), ThumbModel.THUMB_256));
                }
            } else {
                throw new AuthenticationException("## user password is no correct!");
            }
        } catch (Exception e) {
            throw new AuthenticationException("## user password is no correct!");
        }

        // 创建授权用户
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authUser, password, getName());
        return info;
    }

}
