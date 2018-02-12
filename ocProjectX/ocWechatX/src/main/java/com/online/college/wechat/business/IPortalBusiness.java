package com.online.college.wechat.business;

import java.util.List;

import com.online.college.wechat.vo.CourseSectionVO;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-12 14:52
* @version V1.0
 */
public interface IPortalBusiness {

    /**
     * 获取课程章节
     * @param courseId
     * @return
     */
    List<CourseSectionVO> queryCourseSection(Long courseId);

}
