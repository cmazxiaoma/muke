package com.online.college.common.web.shiro.freemarker;

import freemarker.template.SimpleHash;

/**
 *
 * @Description: 将shiro标签注入到FreeMarker
 * @author majinlan
 * @date 2018-02-07 20:02
 * @version V1.0
 */
public class ShiroTags extends SimpleHash {

    public ShiroTags() {
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
    }
}
