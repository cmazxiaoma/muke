package com.online.college.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.consts.domain.ConstsSiteCarousel;
import com.online.college.service.core.consts.service.IConstsSiteCarouselService;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseQueryDto;
import com.online.college.service.core.course.service.ICourseService;

/**
 *
 * @Description: 网站主页
 * @author majinlan
 * @date 2018-02-11 09:46
 * @version V1.0
 */
@Controller
@RequestMapping()
public class PortalController {

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private IConstsSiteCarouselService siteCarouselService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAuthUserService authUserService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");

        // 加载轮播
        List<ConstsSiteCarousel> carouselList = siteCarouselService.queryCarousels(4);
        mv.addObject("carouselList", carouselList);

        // 课程分类(一级分类)
        List<ConstsClassifyVO> classifys = portalBusiness.queryAllClassify();

        // 课程推荐
        portalBusiness.prepareRecomdCourses(classifys);
        mv.addObject("classifys", classifys);

        // 获取5门实战课程推荐， 根据权重(weight)进行排序
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.setCount(5);
        // 实战课，非免费
        queryEntity.setFree(CourseEnum.FREE_NOT.value());
        queryEntity.descSortField("weight");
        List<Course> actionCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("actionCourseList", actionCourseList);

        // 获取5门免费课推荐，根据权重(weight)进行排序
        // 免费课
        queryEntity.setFree(CourseEnum.FREE.value());
        List<Course> freeCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("freeCourseList", freeCourseList);

        // 获取7门java课程, 根据权重(学习数量studyCount)进行排序
        queryEntity.setCount(7);
        queryEntity.setFree(null);
        queryEntity.setSubClassify("java");
        queryEntity.descSortField("studyCount");
        List<Course> javaCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("javaCourseList", javaCourseList);

        // 加载讲师
        List<AuthUser> recomdTeacherList = authUserService.queryRecomd();
        mv.addObject("recomdTeacherList", recomdTeacherList);

        return mv;
    }
}
