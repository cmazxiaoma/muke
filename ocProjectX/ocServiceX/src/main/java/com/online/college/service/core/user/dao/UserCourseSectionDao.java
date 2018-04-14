package com.online.college.service.core.user.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserCourseSection;
import com.online.college.service.core.user.domain.UserCourseSectionDto;

/**
 *
 * @Description: 用户学习课程章节
 * @author majinlan
 * @date 2018-02-08 17:26
 * @version V1.0
 */
public interface UserCourseSectionDao {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public UserCourseSection getById(Long id);

    /**
     * 获取所有
     *
     * @param queryEntity
     * @return
     */
    public List<UserCourseSection> queryAll(UserCourseSection queryEntity);

    /**
     * 获取最新的学习记录
     *
     * @param queryEntity
     * @return
     */
    public UserCourseSection queryLatest(UserCourseSection queryEntity);

    /**
     * 获取总数量
     *
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(UserCourseSection queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public List<UserCourseSectionDto> queryPage(UserCourseSection queryEntity, TailPage<UserCourseSectionDto> page);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void createSelectivity(UserCourseSection entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(UserCourseSection entity);

    /**
     * 根据id选择性更新自动
     *
     * @param entity
     */
    public void updateSelectivity(UserCourseSection entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(UserCourseSection entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(UserCourseSection entity);

}
