package com.online.college.service.core.statics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.service.core.statics.dao.CourseStudyStaticsDao;
import com.online.college.service.core.statics.domain.CourseStudyStaticsDto;
import com.online.college.service.core.statics.domain.StaticsVO;
import com.online.college.service.core.statics.service.IStaticsService;

/**
 *
 * @Description: 统计分析
 * @author majinlan
 * @date 2018-02-08 16:25
 * @version V1.0
 */
@Service
public class StaticsServiceImpl implements IStaticsService {

    @Autowired
    private CourseStudyStaticsDao entityDao;

    @Override
    public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity) {
        List<CourseStudyStaticsDto> list = entityDao.queryCourseStudyStatistics(queryEntity);

        StaticsVO staticsVO = new StaticsVO();
        List<String> categories = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (CourseStudyStaticsDto item : list) {
                categories.add(item.getDateStr());
                data.add(item.getTotalCount());
            }

            staticsVO.setCategories(categories);
            staticsVO.setData(data);
        }

        return staticsVO;
    }

}
