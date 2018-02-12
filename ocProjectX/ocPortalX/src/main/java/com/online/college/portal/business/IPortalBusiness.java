package com.online.college.portal.business;

import java.util.List;
import java.util.Map;

import com.online.college.portal.vo.ConstsClassifyVO;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-09 14:18
* @version V1.0
 */
public interface IPortalBusiness {

    /**
     * 获取所有, 包括一级分类&二级分类
     * @return
     */
    List<ConstsClassifyVO> queryAllClassify();

    /**
     * 获取所有分类
     * @return
     */
    Map<String, ConstsClassifyVO> queryAllClassifyMap();

    /**
     * 为分类设置课程推荐
     * @param classifyVoList
     */
    void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList);
}
