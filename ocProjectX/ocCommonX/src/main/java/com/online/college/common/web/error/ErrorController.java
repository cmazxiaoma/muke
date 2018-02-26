package com.online.college.common.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @Description: ErrorController
 * @author cmazxiaoma
 * @date 2018-02-07 17:00
 * @version V1.0
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * Switching Protocols
     *
     * @return
     */
    @RequestMapping("/101")
    public ModelAndView error101() {
        return new ModelAndView("error/101");
    }

    /**
     * Forbidden
     *
     * @return
     */
    @RequestMapping("/403")
    public ModelAndView error403() {
        return new ModelAndView("error/403");
    }

    /**
     * Not Found
     *
     * @return
     */
    @RequestMapping("/404")
    public ModelAndView error404() {
        return new ModelAndView("error/404");
    }

    /**
     * Internal Server Error
     *
     * @return
     */
    @RequestMapping("/500")
    public ModelAndView error500() {
        return new ModelAndView("error/500");
    }

    /**
     * Method Not Allowed
     *
     * @return
     */
    @RequestMapping("/405")
    public ModelAndView error405() {
        return new ModelAndView("error/405");
    }
}
