package com.online.college.opt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.web.SessionContext;

/**
 *
* @Description: 内容管理首页
* @author cmazxiaoma
* @date 2018-02-11 21:12
* @version V1.0
 */
@Controller
@RequestMapping()
public class CmsController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        if (SessionContext.isLogin()) {
            ModelAndView mv = new ModelAndView("cms/index");
            mv.addObject("curNav", "home");
            return mv;
        } else {
            return new ModelAndView("auth/login");
        }
    }
}
