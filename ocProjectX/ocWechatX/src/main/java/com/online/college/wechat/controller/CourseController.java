package com.online.college.wechat.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.SessionContext;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseComment;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseCommentService;
import com.online.college.service.core.course.service.ICourseSectionService;
import com.online.college.service.core.course.service.ICourseService;
import com.online.college.service.core.user.domain.UserCourseSection;
import com.online.college.service.core.user.service.IUserCourseSectionService;
import com.online.college.wechat.business.IPortalBusiness;
import com.online.college.wechat.vo.CourseSectionVO;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-24 10:22
 * @version V1.0
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private ICourseCommentService courseCommentService;

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private IUserCourseSectionService userCourseSectionService;

    /**
     * 课程详情
     */
    @RequestMapping("/read")
    public ModelAndView read(Long id) {
        ModelAndView mv = new ModelAndView("read");

        // 课程基本信息
        Course course = courseService.getById(id);
        if (null == course) {
            return new ModelAndView("error/404");
        }
        mv.addObject("course", course);

        // 课程章节
        List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(id);
        mv.addObject("chaptSections", chaptSections);

        return mv;
    }

    /**
     * 课程视频学习
     */
    @RequestMapping("/video")
    public ModelAndView video(HttpServletRequest request, Long id) {
        ModelAndView mv = new ModelAndView("video");

        // 课程基本信息
        CourseSection courseSection = courseSectionService.getById(id);
        if (null == courseSection) {
            return new ModelAndView("error/404");
        }

        mv.addObject("courseSection", courseSection);

        // 学习记录
        Long userId = SessionContext.getWxUserId(request);// 当前登录用户id
        if (null != userId) {
            // 获取的学习记录
            UserCourseSection userCourseSection = new UserCourseSection();
            userCourseSection.setUserId(userId);
            userCourseSection.setCourseId(courseSection.getCourseId());
            userCourseSection.setSectionId(courseSection.getId());
            UserCourseSection result = userCourseSectionService.queryLatest(userCourseSection);

            // 如果没有学习过这节课，添加学习记录
            if (null == result) {
                userCourseSection.setCreateTime(new Date());
                userCourseSection.setCreateUser(SessionContext.getWxUsername(request));
                userCourseSection.setUpdateTime(new Date());
                userCourseSection.setUpdateUser(SessionContext.getWxUsername(request));

                userCourseSectionService.createSelectivity(userCourseSection);
            } else {
                result.setUpdateTime(new Date());
                userCourseSectionService.update(result);
            }
        }
        return mv;
    }

    /**
     * 课程评论分页
     */
    @RequestMapping("/comment")
    public ModelAndView comment(CourseComment queryEntity, TailPage<CourseComment> page) {
        ModelAndView mv = new ModelAndView("comment");
        TailPage<CourseComment> commentPage = this.courseCommentService.queryPage(queryEntity, page);
        mv.addObject("page", commentPage);
        return mv;
    }

}
