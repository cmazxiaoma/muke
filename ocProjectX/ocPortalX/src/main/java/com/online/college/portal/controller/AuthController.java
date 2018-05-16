package com.online.college.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;

/**
 *
 * @Description: 用户登录 & 注册
 * @author majinlan
 * @date 2018-02-09 15:04
 * @version V1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register")
    public ModelAndView register() {
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("auth/register");
    }

    /**
     * 实现注册
     *
     * @param authUser
     * @param identiryCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public String doRegister(AuthUser authUser, String identiryCode, HttpServletRequest request) {
        // 验证码判断
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2);
        }

        AuthUser tmpUser = authUserService.getByUsername(authUser.getUsername());

        if (tmpUser != null) {
            return JsonView.render(1);
        } else {
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            authUserService.createSelectivity(authUser);

            return JsonView.render(0);
        }
    }

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("auth/login");
    }

    @RequestMapping(value = "/ajaxlogin")
    @ResponseBody
    public String ajaxLogin(AuthUser user, String identiryCode, Integer rememberMe, HttpServletRequest request) {
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2, "验证码不正确!");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                EncryptUtil.encodedByMD5(user.getPassword()));

        try {
            if (rememberMe != null && rememberMe == 1) {
                token.setRememberMe(true);
            }
            // shiro: 不抛出异常，登录成功
            currentUser.login(token);
            return new JsonView().toString();
        } catch (AuthenticationException e) {
            return JsonView.render(1, "用户名或者密码不正确");
        }
    }

    /**
     * 实现登录
     *
     * @param user
     * @param identiryCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(AuthUser user, String identiryCode, HttpServletRequest request) {
        // 如果已经登录过
        if (SessionContext.getAuthUser() != null) {
            return new ModelAndView("redirect:/user/home.html");
        }

        // 验证码判断
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            ModelAndView modelAndView = new ModelAndView("auth/login");
            modelAndView.addObject("errcode", 1);
            return modelAndView;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                EncryptUtil.encodedByMD5(user.getPassword()));

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            return new ModelAndView("redirect:/user/home.html");
        } catch (AuthenticationException e) {
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 2);
            return mv;
        }
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/index.html");
    }

}
