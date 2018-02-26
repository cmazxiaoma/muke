package com.online.college.common.web.shiro.freemarker;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-07 19:51
 * @version V1.0
 */
public class HasRoleTag extends RoleTag {

    @Override
    protected boolean showTagBody(String roleName) {
        return getSubject() != null && getSubject().hasRole(roleName);
    }
}
