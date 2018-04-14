package com.online.college.opt.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.JsonView;
import com.online.college.service.core.consts.domain.ConstsSiteCarousel;
import com.online.college.service.core.consts.service.IConstsSiteCarouselService;

/**
 *
 * @Description: 轮播图配置
 * @author majinlan
 * @date 2018-02-11 21:31
 * @version V1.0
 */
@Controller
@RequestMapping("/carousel")
public class SiteCarouselController {

    @Autowired
    private IConstsSiteCarouselService constsSiteCarouselService;

    @RequestMapping(value = "/queryPage")
    public ModelAndView queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page) {
        ModelAndView mv = new ModelAndView("cms/carousel/pagelist");
        mv.addObject("curNav", "carousel");
        page.setPageSize(5);
        page = constsSiteCarouselService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);

        return mv;
    }

    @RequestMapping(value = "/toMerge")
    public ModelAndView toMerge(ConstsSiteCarousel entity) {
        ModelAndView mv = new ModelAndView("cms/carousel/merge");
        mv.addObject("curNav", "carousel");

        if (entity.getId() != null) {
            entity = constsSiteCarouselService.getById(entity.getId());

            if (null != entity && StringUtils.isNotEmpty(entity.getPicture())) {
                String pictureUrl = QiniuStorage.getUrl(entity.getPicture(), ThumbModel.THUMB_128);
                entity.setPicture(pictureUrl);
            }
        }
        mv.addObject("entity", entity);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(ConstsSiteCarousel entity, @RequestParam MultipartFile pictureImg) {
        String key = null;

        try {
            if (null != pictureImg && pictureImg.getBytes().length > 0) {
                key = QiniuStorage.uploadImage(pictureImg.getBytes());
                entity.setPicture(key);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (entity.getId() == null) {
            constsSiteCarouselService.createSelectivity(entity);
        } else {
            constsSiteCarouselService.updateSelectivity(entity);
        }

        return new ModelAndView("redirect:/carousel/queryPage.html");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(ConstsSiteCarousel entity) {
        constsSiteCarouselService.delete(entity);
        return new JsonView().toString();
    }
}
