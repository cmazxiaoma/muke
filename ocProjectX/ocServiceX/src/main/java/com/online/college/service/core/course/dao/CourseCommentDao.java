package com.online.college.service.core.course.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.course.domain.CourseComment;

/**
 *
 * @Description: 课程评论
 * @author majinlan
 * @date 2018-02-09 11:48
 * @version V1.0
 */
public interface CourseCommentDao {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public CourseComment getById(Long id);

    /**
     * 获取所有
     *
     * @param queryEntity
     * @return
     */
    public List<CourseComment> queryAll(CourseComment queryEntity);

    /**
     * 获取总数量
     *
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(CourseComment queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public List<CourseComment> queryPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 获取总数量
     *
     * @param queryEntity
     * @return
     */
    public Integer getMyQAItemsCount(CourseComment queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public List<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void create(CourseComment entity);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void createSelectivity(CourseComment entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(CourseComment entity);

    /**
     * 根据id选择性更新自动
     *
     * @param entity
     */
    public void updateSelectivity(CourseComment entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(CourseComment entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(CourseComment entity);

}
