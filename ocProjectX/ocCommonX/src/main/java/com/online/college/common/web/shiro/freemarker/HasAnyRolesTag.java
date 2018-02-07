package com.online.college.common.web.shiro.freemarker;

import org.apache.shiro.subject.Subject;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-07 19:55
* @version V1.0
 */
public class HasAnyRolesTag extends RoleTag {

    /**
     * 角色名称的分隔符
     */
    private static final String ROLE_NAMES_DELIMETER = ",";

    @Override
    protected boolean showTagBody(String roleName) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();

        if (subject != null) {
            for (String role : roleName.split(ROLE_NAMES_DELIMETER)) {
                if (subject.hasRole(role.trim())) {
                    hasAnyRole = true;

                    break;
                }
            }
        }

        return hasAnyRole;
    }
}
