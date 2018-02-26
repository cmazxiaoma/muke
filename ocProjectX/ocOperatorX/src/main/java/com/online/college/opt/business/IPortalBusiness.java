package com.online.college.opt.business;

import java.util.List;
import java.util.Map;

import com.online.college.opt.vo.ConstsClassifyVO;
import com.online.college.opt.vo.CourseSectionVO;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-11 19:16
 * @version V1.0
 */
public interface IPortalBusiness {

    /**
     * 获取所有, 包括一级分类&二级分类
     *
     * @return
     */
    List<ConstsClassifyVO> queryAllClassify();

    /**
     * 获取所有分类
     */
    Map<String, ConstsClassifyVO> queryAllClassifyMap();

    /**
     * 获取课程章节
     */
    List<CourseSectionVO> queryCourseSection(Long courseId);

    /**
     * 为分类设置课程推荐
     */
    void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList);
}
