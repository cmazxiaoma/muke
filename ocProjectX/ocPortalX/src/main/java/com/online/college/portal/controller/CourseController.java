package com.online.college.portal.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVO;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseQueryDto;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;
import com.online.college.service.core.course.service.ICourseService;
import com.online.college.service.core.user.domain.UserCourseSection;
import com.online.college.service.core.user.service.IUserCourseSectionService;

import net.sf.json.JSONObject;

/**
 *
 * @Description: 课程详情信息
 * @author cmazxiaoma
 * @date 2018-02-09 17:09
 * @version V1.0
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseBusiness courseBusiness;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private IUserCourseSectionService userCourseSectionService;

    /**
     * 课程章节界面
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/learn/{courseId}")
    public ModelAndView learn(@PathVariable Long courseId) {
        if (null == courseId) {
            return new ModelAndView("error/404");
        }
        // 获取课程
        Course course = this.courseService.getById(courseId);

        if (null == course) {
            return new ModelAndView("error/404");
        }

        // 获取课程章节
        ModelAndView mv = new ModelAndView("learn");
        List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(courseId);
        mv.addObject("course", course);
        mv.addObject("chaptSections", chaptSections);

        // 获取讲师
        AuthUser courseTeacher = this.authUserService.getByUsername(course.getUsername());

        if (null != courseTeacher && StringUtils.isNotEmpty(courseTeacher.getHeader())) {
            courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
        }
        mv.addObject("courseTeacher", courseTeacher);

        // 获取推荐课程
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.descSortField("weight");
        queryEntity.setCount(5);
        queryEntity.setSubClassify(course.getSubClassify());
        List<Course> recomdCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("recomdCourseList", recomdCourseList);

        // 当前学习的章节
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setCourseId(course.getId());
        userCourseSection.setUserId(SessionContext.getUserId());

        userCourseSection = this.userCourseSectionService.queryLatest(userCourseSection);

        if (null != userCourseSection) {
            CourseSection curCourseSection = this.courseSectionService.getById(userCourseSection.getSectionId());
            mv.addObject("curCourseSection", curCourseSection);
        }

        return mv;
    }

    /**
     * 视频学习界面
     *
     * @param sectionId
     * @return
     */
    @RequestMapping("/video/{sectionId}")
    public ModelAndView video(@PathVariable Long sectionId) {
        if (null == sectionId) {
            return new ModelAndView("error/404");
        }

        CourseSection courseSection = courseSectionService.getById(sectionId);

        if (null == courseSection) {
            return new ModelAndView("error/404");
        }

        // 课程章节
        ModelAndView mv = new ModelAndView("video");
        List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(courseSection.getCourseId());

        mv.addObject("courseSection", courseSection);
        mv.addObject("chaptSections", chaptSections);

        // 学习记录
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(SessionContext.getUserId());
        userCourseSection.setCourseId(courseSection.getCourseId());
        userCourseSection.setSectionId(courseSection.getId());
        UserCourseSection result = userCourseSectionService.queryLatest(userCourseSection);

        // 如果没有，则插入
        if (null == result) {
            userCourseSection.setCreateTime(new Date());
            userCourseSection.setUpdateTime(new Date());
            userCourseSection.setCreateUser(SessionContext.getUsername());
            userCourseSection.setUpdateUser(SessionContext.getUsername());

            userCourseSectionService.createSelectivity(userCourseSection);
        } else {
            // 更新
            result.setUpdateTime(new Date());
            userCourseSectionService.update(result);
        }

        return mv;
    }

    /**
     * 加载当前用户学习最新课程
     *
     * @return
     */
    @RequestMapping(value = "/getCurLeanInfo")
    @ResponseBody
    public String getCurLearnInfo() {
        JsonView jsonView = new JsonView();

        if (SessionContext.isLogin()) {
            UserCourseSection userCourseSection = new UserCourseSection();
            userCourseSection.setUserId(SessionContext.getUserId());
            userCourseSection = this.userCourseSectionService.queryLatest(userCourseSection);

            if (null != userCourseSection) {
                JSONObject jsonObject = new JSONObject();
                CourseSection curCourseSection = this.courseSectionService.getById(userCourseSection.getSectionId());
                jsonObject.put("curCourseSection", curCourseSection);
                Course curCourse = this.courseService.getById(userCourseSection.getCourseId());
                jsonObject.put("curCourse", curCourse);
                jsonView.setData(jsonObject);
            }
        }

        return jsonView.toString();
    }
}
