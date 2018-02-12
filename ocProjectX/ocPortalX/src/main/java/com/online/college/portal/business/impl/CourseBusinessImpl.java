package com.online.college.portal.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVO;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;

/**
 *
* @Description: 课程业务层
* @author cmazxiaoma
* @date 2018-02-09 14:22
* @version V1.0
 */
@Service
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    private ICourseSectionService courseSectionService;

    @Override
    public List<CourseSectionVO> queryCourseSection(Long courseId) {
        List<CourseSectionVO> resultList = new ArrayList<CourseSectionVO>();
        CourseSection courseSection = new CourseSection();
        courseSection.setCourseId(courseId);
        //设置上架
        courseSection.setOnsale(CourseEnum.ONSALE.value());

        Map<Long, CourseSectionVO> tmpMap = new LinkedHashMap<Long, CourseSectionVO>();
        Iterator<CourseSection> it = courseSectionService.queryAll(courseSection).iterator();

        while (it.hasNext()) {
            CourseSection item = it.next();

            if (Long.valueOf(0).equals(item.getParentId())) {
                CourseSectionVO vo = new CourseSectionVO();
                BeanUtils.copyProperties(item, vo);
                tmpMap.put(vo.getId(), vo);
            } else {
                //小节加入到大章节里面
                tmpMap.get(item.getParentId()).getSections().add(item);
            }
        }

        for (CourseSectionVO vo : tmpMap.values()) {
            resultList.add(vo);
        }

        return resultList;
    }

}
