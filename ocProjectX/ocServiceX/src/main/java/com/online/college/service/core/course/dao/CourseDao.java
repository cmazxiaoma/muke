package com.online.college.service.core.course.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseQueryDto;

public interface CourseDao {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public Course getById(Long id);

    /**
     * 根据条件获取所有
     *
     * @param queryEntity
     *            查询条件
     * @return
     */
    public List<Course> queryList(CourseQueryDto queryEntity);

    /**
     * 获取总数量
     *
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(Course queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public List<Course> queryPage(Course queryEntity, TailPage<Course> page);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void create(Course entity);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void createSelectivity(Course entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(Course entity);

    /**
     * 根据id选择性更新自动
     *
     * @param entity
     */
    public void updateSelectivity(Course entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(Course entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(Course entity);

}
