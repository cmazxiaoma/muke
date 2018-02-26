package com.online.college.opt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.opt.business.IPortalBusiness;
import com.online.college.opt.vo.ConstsClassifyVO;
import com.online.college.service.core.consts.domain.ConstsClassify;
import com.online.college.service.core.consts.service.IConstsClassifyService;

/**
 *
 * @Description: 课程分类管理
 * @author cmazxiaoma
 * @date 2018-02-11 20:59
 * @version V1.0
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private IConstsClassifyService constsClassifyService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        return JsonView.render(constsClassifyService.getById(id));
    }

    @RequestMapping(value = "/index")
    public ModelAndView classifyIndex(ConstsClassify queryEntity, TailPage<ConstsClassify> page) {
        ModelAndView mv = new ModelAndView("cms/classify/classifyIndex");
        mv.addObject("curNav", "classify");
        Map<String, ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();

        // 所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();

        for (ConstsClassifyVO vo : classifyMap.values()) {
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);

        List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();

        for (ConstsClassifyVO vo : classifyMap.values()) {
            subClassifys.addAll(vo.getSubClassifyList());
        }
        // 所有二级分类
        mv.addObject("subClassifys", subClassifys);

        return mv;
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(ConstsClassify entity) {
        if (entity.getId() == null) {
            ConstsClassify tmpEntity = this.constsClassifyService.getByCode(entity.getCode());

            if (tmpEntity != null) {
                return JsonView.render(1, "此编码已存在");
            }
            this.constsClassifyService.create(entity);
        } else {
            this.constsClassifyService.update(entity);
        }

        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(ConstsClassify entity) {
        this.constsClassifyService.deleteLogic(entity);
        return new JsonView().toString();
    }
}
