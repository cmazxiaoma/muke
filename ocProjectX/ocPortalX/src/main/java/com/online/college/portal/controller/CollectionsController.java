package com.online.college.portal.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.user.domain.UserCollections;
import com.online.college.service.core.user.service.IUserCollectionsService;

/**
 *
 * @Description: 用户收藏
 * @author cmazxiaoma
 * @date 2018-02-09 15:48
 * @version V1.0
 */
@Controller
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private IUserCollectionsService userCollectionsService;

    /**
     * 收藏
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/doCollection")
    @ResponseBody
    public String doCollection(Long courseId) {
        // 获得当前用户
        Long curUserId = SessionContext.getUserId();
        UserCollections userCollections = new UserCollections();

        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());
        userCollections.setObjectId(courseId);

        List<UserCollections> list = userCollectionsService.queryAll(userCollections);

        if (CollectionUtils.isNotEmpty(list)) {
            // 取消收藏
            userCollectionsService.delete(list.get(0));
            return new JsonView(0).toString();
        } else {
            userCollections.setCreateTime(new Date());
            userCollectionsService.createSelectivity(userCollections);
            // 收藏成功
            return new JsonView(1).toString();
        }
    }

    /**
     * 是否已经收藏
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/isCollection")
    @ResponseBody
    public String isCollection(Long courseId) {
        Long curUserId = SessionContext.getUserId();
        UserCollections userCollections = new UserCollections();

        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());
        userCollections.setObjectId(courseId);

        List<UserCollections> list = userCollectionsService.queryAll(userCollections);

        // 是否已经收藏
        if (CollectionUtils.isNotEmpty(list)) {
            return new JsonView(1).toString();
        } else {
            return new JsonView(0).toString();
        }
    }

}
