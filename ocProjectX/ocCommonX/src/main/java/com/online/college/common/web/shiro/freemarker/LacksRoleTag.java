package com.online.college.common.web.shiro.freemarker;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-07 19:47
* @version V1.0
 */
public class LacksRoleTag extends RoleTag {

    @Override
    protected boolean showTagBody(String roleName) {
        boolean hasRole = getSubject() != null && getSubject().hasRole(roleName);

        return !hasRole;
    }
}
