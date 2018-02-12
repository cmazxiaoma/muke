package com.online.college.common.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import com.online.college.common.web.auth.SessionUser;

/**
 *
* @Description: session工具类
* @author cmazxiaoma
* @date 2018年2月5日
* @version V1.0
 */
public class SessionContext {
    public static final String IDENTIFY_CODE_kEY = "_consts_identify_code_key_";
    public static final String AUTH_USER_KEY = "_consts_auth_user_key_";

    /**
     * 获取当前用户的userId
     * @return
     */
    public static Long getUserId() {
        if (getAuthUser() != null) {
            return getAuthUser().getUserId();
        }
        return null;
    }

    /**
     * 获取当前用户的userName
     * @return
     */
    public static String getUsername() {
        if (getAuthUser() != null) {
            return getAuthUser().getUsername();
        }
        return null;
    }

    /**
     * 如果是微信登录，获取当前用户userId
     * @param request
     * @return
     */
    public static Long getWxUserId(HttpServletRequest request) {
        if (getWxAuthUser(request) != null) {
            return getWxAuthUser(request).getUserId();
        }
        return null;
    }

    /**
     * 如果是微信登录，获取当前用户userName
     * @param request
     * @return
     */
    public static String getWxUsername(HttpServletRequest request) {
        if (getWxAuthUser(request) != null) {
            return getWxAuthUser(request).getUsername();
        }
        return null;
    }

    /**
     * 判断是否微信登录
     * @param request
     * @return
     */
    public static boolean isWxLogin(HttpServletRequest request) {
       return getWxAuthUser(request) != null;
    }

    /**
     * 如果是微信登录，获取当前用户信息
     * @param request
     * @return
     */
    public static SessionUser getWxAuthUser(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        Object object = SessionContext.getAttribute(request, sessionId);

        if (object != null) {
            return (SessionUser) object;
        }
        return null;
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static SessionUser getAuthUser() {
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            return (SessionUser) SecurityUtils.getSubject().getPrincipal();
        }
        return null;
    }

    /**
     * 获取验证码
     * @param request
     * @return
     */
    public static String getIdentifyCode(HttpServletRequest request) {
        if (request.getSession().getAttribute(IDENTIFY_CODE_kEY) != null) {
            return getAttribute(request, IDENTIFY_CODE_kEY).toString();
        } else {
            return null;
        }
    }

    /**
     * 获得属性
     * @param request
     * @param key
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    /**
     * 设置属性
     * @param request
     * @param key
     * @param value
     */
    public static void setAttribute(HttpServletRequest request, String key, String value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * 移除属性
     * @param request
     * @param key
     */
    public static void removeAttribute(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }

    /**
     * 判断是否登录
     * @return
     */
    public static boolean isLogin() {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser != null && currentUser.getPrincipal() != null) {
            return true;
        }
        return false;
    }

    /**
     * shiro logout
     */
    public static void shiroLogout() {
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
