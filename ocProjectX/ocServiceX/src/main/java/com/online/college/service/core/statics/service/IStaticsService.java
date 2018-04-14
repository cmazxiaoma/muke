package com.online.college.service.core.statics.service;

import com.online.college.service.core.statics.domain.CourseStudyStaticsDto;
import com.online.college.service.core.statics.domain.StaticsVO;

/**
 *
 * @Description: 课程报表统计
 * @author majinlan
 * @date 2018-02-08 16:24
 * @version V1.0
 */
public interface IStaticsService {

    /**
     * 统计课程学习情况
     *
     * @param queryEntity
     * @return
     */
    public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);

}
