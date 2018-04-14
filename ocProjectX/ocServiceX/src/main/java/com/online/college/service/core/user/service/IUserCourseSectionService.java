package com.online.college.service.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserCourseSection;
import com.online.college.service.core.user.domain.UserCourseSectionDto;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 18:23
 * @version V1.0
 */
public interface IUserCourseSectionService {

    public UserCourseSection getById(Long id);

    public List<UserCourseSection> queryAll(UserCourseSection queryEntity);

    public UserCourseSection queryLatest(UserCourseSection queryEntity);

    public TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity, TailPage<UserCourseSectionDto> page);

    public void createSelectivity(UserCourseSection entity);

    public void update(UserCourseSection entity);

    public void updateSelectivity(UserCourseSection entity);

    public void delete(UserCourseSection entity);

    public void deleteLogic(UserCourseSection entity);

}
