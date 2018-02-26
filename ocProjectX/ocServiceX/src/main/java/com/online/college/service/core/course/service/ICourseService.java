package com.online.college.service.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseQueryDto;

/**
 *
 * @Description: 课程服务层
 * @author cmazxiaoma
 * @date 2018-02-09 13:42
 * @version V1.0
 */
public interface ICourseService {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public Course getById(Long id);

    /**
     * 获取所有
     *
     * @param queryEntity
     * @return
     */
    public List<Course> queryList(CourseQueryDto queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public TailPage<Course> queryPage(Course queryEntity, TailPage<Course> page);

    /**
     * 创建
     *
     * @param entity
     */
    public void createSelectivity(Course entity);

    /**
     * 根据id 进行可选性更新
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
