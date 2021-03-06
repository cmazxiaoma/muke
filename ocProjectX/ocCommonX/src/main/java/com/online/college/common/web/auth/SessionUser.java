package com.online.college.common.web.auth;

import java.util.Set;

/**
 *
 * @Description: 权限用户
 * @author majinlan
 * @date 2018年2月5日
 * @version V1.0
 */
public interface SessionUser {

    String getUsername();

    Long getUserId();

    Set<String> getPermissions();
}
