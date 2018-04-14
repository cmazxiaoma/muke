package com.online.college.portal.business;

import java.util.List;

import com.online.college.portal.vo.CourseSectionVO;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-09 14:21
 * @version V1.0
 */
public interface ICourseBusiness {

    List<CourseSectionVO> queryCourseSection(Long courseId);

}
