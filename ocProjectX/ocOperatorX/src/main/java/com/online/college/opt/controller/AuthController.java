package com.online.college.opt.controller;

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
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-11 20:41
 * @version V1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

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
        if (SessionContext.getAuthUser() != null) {
            return new ModelAndView("redirect:/index.html");
        }

        // 验证码判断
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 1);
            return mv;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                EncryptUtil.encodedByMD5(user.getPassword()));

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);// shiro实现登录
            return new ModelAndView("redirect:/index.html");
        } catch (AuthenticationException e) { // 登录失败
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 2);
            return mv;
        }
    }

    /**
     * 注册页面
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

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/index.html");
    }

}
