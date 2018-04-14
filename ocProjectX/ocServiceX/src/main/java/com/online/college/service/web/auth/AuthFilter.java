package com.online.college.service.web.auth;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.online.college.common.util.JsonUtil;
import com.online.college.common.web.HttpHelper;
import com.online.college.common.web.JsonView;

/**
 *
 * @Description: shiro对用户是否登录的filter
 * @author majinlan
 * @date 2018-02-08 10:40
 * @version V1.0
 */
public class AuthFilter extends FormAuthenticationFilter {

    private static final Integer SHIRO_TIME_OUT = 1001;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 获取请求路径
        String login = httpServletRequest.getServletPath();

        // 判断请求路径是否为登录页，如果为登录页则放行
        if (login.equals("/index.html")) {
            return true;
        }

        // 获取当前登录用户
        Subject subject = getSubject(request, response);

        // 判断是否授权
        if (subject.isAuthenticated()) {
            return true;
        }

        // 判断是否为ajax请求
        if (HttpHelper.isAjaxRequest(httpServletRequest)) {
            JsonView jsonView = new JsonView();
            jsonView.setMessage("SHIRO登录超时");
            jsonView.setErrcode(SHIRO_TIME_OUT);
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            PrintWriter out = httpServletResponse.getWriter();
            httpServletResponse.setContentType("application/json");

            out.write(JsonUtil.toJson(jsonView));
            out.flush();
            out.close();
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }

        // 如果没有授权则跳转到登录界面
        return false;
    }
}
