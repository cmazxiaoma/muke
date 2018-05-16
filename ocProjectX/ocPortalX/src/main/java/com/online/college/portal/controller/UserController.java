package com.online.college.portal.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;
import com.online.college.service.core.course.domain.CourseComment;
import com.online.college.service.core.course.service.ICourseCommentService;
import com.online.college.service.core.user.domain.UserCollections;
import com.online.college.service.core.user.domain.UserCourseSection;
import com.online.college.service.core.user.domain.UserCourseSectionDto;
import com.online.college.service.core.user.domain.UserFollowStudyRecord;
import com.online.college.service.core.user.service.IUserCollectionsService;
import com.online.college.service.core.user.service.IUserCourseSectionService;
import com.online.college.service.core.user.service.IUserFollowsService;

/**
 *
 * @Description: 个人中心
 * @author majinlan
 * @date 2018-02-11 10:05
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private IUserCourseSectionService userCourseSectionService;

    @Autowired
    private IUserCollectionsService userCollectionsService;

    @Autowired
    private IUserFollowsService userFollowsService;

    @Autowired
    private ICourseCommentService courseCommentService;

    /**
     * 首页
     *
     * @param page
     * @return
     */
    @RequestMapping("/home")
    public ModelAndView index(TailPage<UserFollowStudyRecord> page) {
        ModelAndView mv = new ModelAndView("user/home");
        mv.addObject("curNav", "home");

        // 加载关注用户的动态
        UserFollowStudyRecord queryEntity = new UserFollowStudyRecord();
        queryEntity.setUserId(SessionContext.getUserId());
        page = userFollowsService.queryUserFollowStudyRecordPage(queryEntity, page);

        // 处理用户头像
        for (UserFollowStudyRecord item : page.getItems()) {
            if (StringUtils.isNotEmpty(item.getHeader())) {
                item.setHeader(QiniuStorage.getUrl(item.getHeader()));
            }
        }
        mv.addObject("page", page);

        return mv;
    }

    /**
     * 我的课程
     */
    @RequestMapping("/course")
    public ModelAndView course(TailPage<UserCourseSectionDto> page) {
        ModelAndView mv = new ModelAndView("user/course");
        mv.addObject("curNav", "course");

        UserCourseSection queryEntity = new UserCourseSection();
        queryEntity.setUserId(SessionContext.getUserId());
        page = userCourseSectionService.queryPage(queryEntity, page);

        mv.addObject("page", page);
        return mv;
    }

    /**
     * 我的收藏
     */
    @RequestMapping("/collect")
    public ModelAndView collect(TailPage<UserCollections> page) {
        ModelAndView mv = new ModelAndView("user/collect");
        mv.addObject("curNav", "collect");

        UserCollections queryEntity = new UserCollections();
        queryEntity.setUserId(SessionContext.getUserId());
        page = userCollectionsService.queryPage(queryEntity, page);

        mv.addObject("page", page);
        return mv;
    }

    /**
     * 信息
     *
     * @return
     */
    @RequestMapping("/info")
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView("user/info");
        mv.addObject("curNav", "info");

        AuthUser authUser = authUserService.getById(SessionContext.getUserId());

        if (null != authUser && StringUtils.isNotEmpty(authUser.getHeader())) {
            authUser.setHeader(QiniuStorage.getUrl(authUser.getHeader()));
        }
        mv.addObject("authUser", authUser);
        return mv;
    }

    /**
     * 保存信息
     *
     * @param authUser
     * @param pictureImg
     * @return
     */
    @RequestMapping("/saveInfo")
    @ResponseBody
    public String saveInfo(AuthUser authUser, @RequestParam MultipartFile pictureImg) {
        try {
            authUser.setId(SessionContext.getUserId());

            if (null != pictureImg && pictureImg.getBytes().length > 0) {
                String key = QiniuStorage.uploadImage(pictureImg.getBytes());
                authUser.setHeader(key);
            }
            this.authUserService.updateSelectivity(authUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonView().toString();
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("/passwd")
    public ModelAndView passwd() {
        ModelAndView mv = new ModelAndView("user/passwd");
        mv.addObject("curNav", "passwd");

        return mv;
    }

    /**
     * 保存修改后的密码
     *
     * @param oldPassword
     * @param password
     * @param rePassword
     * @return
     */
    @RequestMapping("/savePasswd")
    @ResponseBody
    public String savePasswd(String oldPassword, String password, String rePassword) {
        AuthUser currentUser = authUserService.getById(SessionContext.getUserId());

        if (null == currentUser) {
            return JsonView.render(1, "用户不存在!");
        }
        oldPassword = EncryptUtil.encodedByMD5(oldPassword.trim());

        if (!oldPassword.equals(currentUser.getPassword())) {
            return JsonView.render(1, "旧密码不正确!");
        }

        if (StringUtils.isEmpty(password.trim())) {
            return JsonView.render(1, "新密码不能为空!");
        }

        if (password.length() < 6) {
            return JsonView.render(1, "新密码长度不能低于6位");
        }

        if (!password.trim().equals(rePassword.trim())) {
            return JsonView.render(1, "新密码与重复密码不一致!");
        }
        currentUser.setPassword(EncryptUtil.encodedByMD5(password.trim()));
        this.authUserService.updateSelectivity(currentUser);

        return new JsonView().toString();
    }

    /**
     * 问答
     *
     * @param page
     * @return
     */
    @RequestMapping("/qa")
    public ModelAndView qa(TailPage<CourseComment> page) {
        ModelAndView mv = new ModelAndView("user/qa");
        mv.addObject("curNav", "qa");
        CourseComment queryEntity = new CourseComment();
        queryEntity.setUsername(SessionContext.getUsername());
        page = courseCommentService.queryMyQAItemsPage(queryEntity, page);
        mv.addObject("page", page);

        return mv;
    }

}
