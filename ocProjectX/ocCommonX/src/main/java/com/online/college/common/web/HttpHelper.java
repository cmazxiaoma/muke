package com.online.college.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.WebUtils;

/**
 *
 * @Description: http工具类
 * @author cmazxiaoma
 * @date 2018年2月3日
 * @version V1.0
 */
public class HttpHelper extends WebUtils {

    /**
     * 获得网站域名, 比如cmazxiaoma.com
     *
     * @param request
     * @return
     */
    public static String getDomain(HttpServletRequest request) {
        return request.getServerName();
    }

    /**
     * 获取协议+域名, 比如http://cmazxiaoma.com
     *
     * @param request
     * @return
     */
    public static String getHttpDomain(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName();
    }

    /**
     * 获得类似 http://cmazxiaoma.com:8080/ocPortal的url
     *
     * @param request
     * @return
     */
    public static String getContextHttpUri(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    }

    /**
     * 获取应用的绝对路径
     *
     * @param request
     * @return
     */
    public static String getRealPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 获取带根路径的完整url
     *
     * @param request
     * @return
     */
    public static String getRequestFullUri(HttpServletRequest request) {
        String port = "";

        if (request.getServerPort() != 80) {
            port = ":" + request.getServerPort();
        }
        return request.getScheme() + "://" + request.getServerName() + port + request.getContextPath()
                + request.getServletPath();
    }

    /**
     * 获取不带根路径的完整url
     *
     * @param request
     * @return
     */
    public static String getRequestFullUriNoContextPath(HttpServletRequest request) {
        String port = "";

        if (request.getServerPort() != 80) {
            port = ":" + request.getServerPort();
        }
        return request.getScheme() + "://" + request.getServerName() + port + request.getServletPath();
    }

    /**
     * 判断该请求是否为Ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return !StringUtils.isEmpty(header) && "XMLHttpRequest".equals(header);
    }

    /**
     * 重定向本项目的url
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param url
     */
    public static void redirectUrl(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            String url) {
        try {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重定向到http://的url
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param url
     */
    public static void redirectHttpUrl(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            String url) {
        try {
            httpServletResponse.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            if (ip.indexOf("::ffff:") != -1) {
                ip = ip.replace("::ffff:", "");
            }
            int index = ip.indexOf(",");

            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    @SuppressWarnings("unused")
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

}
