package com.online.college.portal.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;
import com.online.college.service.core.consts.domain.ConstsClassify;
import com.online.college.service.core.consts.service.IConstsClassifyService;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseQueryDto;
import com.online.college.service.core.course.service.ICourseService;

/**
 *
* @Description: 首页业务层
* @author cmazxiaoma
* @date 2018-02-09 14:34
* @version V1.0
 */
@Service
public class PortalBusinessImpl implements IPortalBusiness {

    @Autowired
    private IConstsClassifyService constsClassifyService;

    @Autowired
    private ICourseService courseService;

    /**
     * 获取所有, 包括一级分类&二级分类
     */
    @Override
    public List<ConstsClassifyVO> queryAllClassify() {
        List<ConstsClassifyVO> resultList = new ArrayList<ConstsClassifyVO>();

        for (ConstsClassifyVO vo : this.queryAllClassifyMap().values()) {
            resultList.add(vo);
        }

        return resultList;
    }

    /**
     * 获取所有分类
     */
    @Override
    public Map<String, ConstsClassifyVO> queryAllClassifyMap() {
        Map<String, ConstsClassifyVO> resultMap = new LinkedHashMap<String, ConstsClassifyVO>();
        Iterator<ConstsClassify> it = constsClassifyService.queryAll().iterator();

        while (it.hasNext()) {
            ConstsClassify constsClassify = it.next();

            //一级分类
            if ("0".equals(constsClassify.getParentCode())) {
                ConstsClassifyVO vo = new ConstsClassifyVO();
                BeanUtils.copyProperties(constsClassify, vo);
                resultMap.put(vo.getCode(), vo);
            } else {
                //二级分类
                if (null != resultMap.get(constsClassify.getParentCode())) {
                    resultMap.get(constsClassify.getParentCode())
                        .getSubClassifyList()
                        .add(constsClassify);
                }
            }
        }
        return resultMap;
    }

    /**
     * 为分类设置课程推荐
     */
    @Override
    public void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList) {
        if (CollectionUtils.isNotEmpty(classifyVoList)) {
            for (ConstsClassifyVO item : classifyVoList) {
                CourseQueryDto courseQueryDto = new CourseQueryDto();
                courseQueryDto.setCount(5);
                courseQueryDto.descSortField("weight");
                //分类code
                courseQueryDto.setClassify(item.getCode());

                List<Course> tmpList = this.courseService.queryList(courseQueryDto);

                if (CollectionUtils.isNotEmpty(tmpList)) {
                    item.setRecomdCourseList(tmpList);
                }
            }
        }
    }

}
