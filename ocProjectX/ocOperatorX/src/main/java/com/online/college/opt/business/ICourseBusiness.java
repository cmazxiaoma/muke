package com.online.college.opt.business;

import java.util.List;

import com.online.college.opt.vo.CourseSectionVO;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-11 19:08
 * @version V1.0
 */
public interface ICourseBusiness {

    /**
     * 获取课程章节
     *
     * @param courseId
     * @return
     */
    List<CourseSectionVO> queryCourseSection(Long courseId);

}
