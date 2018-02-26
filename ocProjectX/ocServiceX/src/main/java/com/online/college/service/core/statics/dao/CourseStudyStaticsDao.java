package com.online.college.service.core.statics.dao;

import java.util.List;

import com.online.college.service.core.statics.domain.CourseStudyStaticsDto;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-08 16:17
 * @version V1.0
 */
public interface CourseStudyStaticsDao {

    /**
     * 统计课程学习情况
     *
     * @param queryEntity
     * @return
     */
    public List<CourseStudyStaticsDto> queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
}
