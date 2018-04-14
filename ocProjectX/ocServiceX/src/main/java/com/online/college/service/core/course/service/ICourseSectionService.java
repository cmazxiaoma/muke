package com.online.college.service.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.course.domain.CourseSection;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-09 13:38
 * @version V1.0
 */
public interface ICourseSectionService {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public CourseSection getById(Long id);

    /**
     * 获取所有
     *
     * @param queryEntity
     * @return
     */
    public List<CourseSection> queryAll(CourseSection queryEntity);

    /**
     * 获取课程章最大的sort
     *
     * @param courseId
     * @return
     */
    public Integer getMaxSort(Long courseId);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public TailPage<CourseSection> queryPage(CourseSection queryEntity, TailPage<CourseSection> page);

    /**
     * 创建
     *
     * @param entity
     */
    public void createSelectivity(CourseSection entity);

    /**
     * 批量创建
     *
     * @param entityList
     */
    public void createList(List<CourseSection> entityList);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(CourseSection entity);

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    public void updateSelectivity(CourseSection entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(CourseSection entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(CourseSection entity);

    /**
     * 比当前sort大的，正序排序的第一个
     *
     * @param curCourseSection
     * @return
     */
    public CourseSection getSortSectionMax(CourseSection curCourseSection);

    /**
     * 比当前sort小的，倒序排序的第一个
     *
     * @param curCourseSection
     * @return
     */
    public CourseSection getSortSectionMin(CourseSection curCourseSection);
}
