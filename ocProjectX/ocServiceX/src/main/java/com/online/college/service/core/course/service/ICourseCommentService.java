package com.online.college.service.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.course.domain.CourseComment;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-09 13:38
 * @version V1.0
 */
public interface ICourseCommentService {

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
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public TailPage<CourseComment> queryPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 分页获取我的所有课程的qa
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public TailPage<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 创建
     *
     * @param entity
     */
    public void create(CourseComment entity);

    /**
     * 创建
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
     * 根据id 进行可选性更新
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
