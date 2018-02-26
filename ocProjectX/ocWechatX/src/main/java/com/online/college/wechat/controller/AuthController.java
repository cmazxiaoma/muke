package com.online.college.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.SessionContext;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;
import com.online.college.wechat.wxapi.process.WxMemoryCacheClient;
import com.online.college.wechat.wxapi.vo.OAuthUserInfo;

/**
 *
 * @Description: 用户登录
 * @author cmazxiaoma
 * @date 2018-02-24 10:21
 * @version V1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        /*
         * if(SessionContext.isWxLogin()){ return new
         * ModelAndView("redirect:/index.html"); }
         */

        return new ModelAndView("login");
    }

    // 实现登录
    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(AuthUser user, String toUrl, HttpServletRequest request) {
        // 如果已经登录过
        /*
         * if(SessionContext.getWxAuthUser(request) != null){ return new
         * ModelAndView("redirect:/index.html"); }
         */

        // 判断用户名密码是否正确
        AuthUser tmpAuthUser = new AuthUser();
        tmpAuthUser.setUsername(user.getUsername());
        tmpAuthUser.setPassword(EncryptUtil.encodedByMD5(user.getPassword()));
        tmpAuthUser = authUserService.getByUsernameAndPassword(tmpAuthUser);

        if (null != tmpAuthUser) {// 登录成功

            // 获取当前用户openid
            String openid = WxMemoryCacheClient.getOpenid(request.getSession().getId());

            // 获取授权用户的信息
            OAuthUserInfo userInfo = WxMemoryCacheClient.getOAuthUserInfo(request.getSession().getId());

            if (StringUtils.isNotEmpty(openid)) {
                tmpAuthUser.setOpenId(openid);// 设置openid，并更新数据库
            } else {
                // 跳转到一个必须关注提示的页面
            }

            if (userInfo != null) {
                tmpAuthUser.setCity(userInfo.getCity());
                tmpAuthUser.setProvince(userInfo.getProvince());
                tmpAuthUser.setNickname(userInfo.getNickname());
            } else {
                ModelAndView mv = new ModelAndView("login");
                mv.addObject("errcode", 3);
                return mv;
            }

            // 更新数据库
            authUserService.updateSelectivity(tmpAuthUser);

            // 模拟session存储数据
            String sessionId = request.getSession().getId();
            tmpAuthUser.setHeader(QiniuStorage.getUrl(tmpAuthUser.getHeader()));
            SessionContext.setAttribute(request, sessionId, tmpAuthUser);
            return new ModelAndView("redirect:/user/index.html");
        }

        // 登录失败
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("errcode", 2);
        return mv;
    }

}
