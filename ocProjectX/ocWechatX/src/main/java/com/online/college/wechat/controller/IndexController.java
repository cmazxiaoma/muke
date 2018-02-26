package com.online.college.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.service.ICourseService;

/**
 *
 * @Description: M站
 * @author cmazxiaoma
 * @date 2018-02-24 10:22
 * @version V1.0
 */
@Controller
@RequestMapping()
public class IndexController {

    @Autowired
    private ICourseService courseService;

    // memcache客户端
    /*
     * @Autowired MemcachedClient memcachedClient;
     */
    /**
     * 首页
     */
    @RequestMapping("/index")
    public ModelAndView index(TailPage<Course> page) {
        ModelAndView mv = new ModelAndView("index");

        // 只展示第一页的课程
        Course queryEntity = new Course();
        queryEntity.setOnsale(CourseEnum.ONSALE.value());

        page.descSortField("weight");

        page = this.courseService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        return mv;
    }

}
