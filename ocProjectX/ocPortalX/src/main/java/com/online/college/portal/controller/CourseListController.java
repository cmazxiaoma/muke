package com.online.college.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.consts.domain.ConstsClassify;
import com.online.college.service.core.consts.service.IConstsClassifyService;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.service.ICourseService;

/**
 *
 * @Description: 课程分类页
 * @author majinlan
 * @date 2018-02-11 09:04
 * @version V1.0
 */
@Controller
@RequestMapping("/course")
public class CourseListController {

    @Autowired
    private IConstsClassifyService constsClassifyService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private ICourseService courseService;

    /**
     * 课程分类页
     *
     * @param c
     *            分类code
     * @param sort
     *            排序
     * @param page
     *            分页
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(String c, String sort, TailPage<Course> page) {
        ModelAndView mv = new ModelAndView("list");
        String curCode = "-1";
        String curSubCode = "-2";

        // 加载所有课程分类
        Map<String, ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();

        // 所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();

        for (ConstsClassifyVO vo : classifyMap.values()) {
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);

        // 当前分类
        ConstsClassify curClassify = constsClassifyService.getByCode(c);

        // 没有此分类，则加载所有二级分类
        if (null == curClassify) {
            List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();

            for (ConstsClassifyVO vo : classifyMap.values()) {
                subClassifys.addAll(vo.getSubClassifyList());
            }
        } else {
            // 如果是二级分类
            if (!"0".endsWith(curClassify.getParentCode())) {
                curSubCode = curClassify.getCode();
                curCode = curClassify.getParentCode();
                // 此分类平级的二级分类
                mv.addObject("subClassifys", classifyMap.get(curClassify.getParentCode()).getSubClassifyList());
            } else {
                // 如果是一级分类
                curCode = curClassify.getCode();
                // 此分类下的二级分类
                mv.addObject("subClassifys", classifyMap.get(curClassify.getCode()).getSubClassifyList());
            }
        }
        mv.addObject("curCode", curCode);
        mv.addObject("curSubCode", curSubCode);

        // 分页排序数据
        Course queryEntity = new Course();

        if (!"-1".equals(curCode)) {
            queryEntity.setClassify(curCode);
        }

        if (!"-2".equals(curSubCode)) {
            queryEntity.setSubClassify(curSubCode);
        }

        // 排序参数
        if ("pop".equals(sort)) {
            page.descSortField("studyCount");
        } else {
            sort = "last";
            page.descSortField("id");
        }

        mv.addObject("sort", sort);

        // 分页参数
        queryEntity.setOnsale(CourseEnum.ONSALE.value());
        page = this.courseService.queryPage(queryEntity, page);
        mv.addObject("page", page);

        return mv;
    }
}
