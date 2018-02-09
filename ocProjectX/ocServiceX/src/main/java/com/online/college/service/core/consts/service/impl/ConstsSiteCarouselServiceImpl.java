package com.online.college.service.core.consts.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.service.core.consts.dao.ConstsSiteCarouselDao;
import com.online.college.service.core.consts.domain.ConstsSiteCarousel;
import com.online.college.service.core.consts.service.IConstsSiteCarouselService;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-08 15:35
* @version V1.0
 */
@Service
public class ConstsSiteCarouselServiceImpl implements IConstsSiteCarouselService {

    @Autowired
    private ConstsSiteCarouselDao constsSiteCarouselDao;

    @Override
    public ConstsSiteCarousel getById(Long id) {
        return constsSiteCarouselDao.getById(id);
    }

    @Override
    public List<ConstsSiteCarousel> queryCarousels(Integer count) {
        List<ConstsSiteCarousel> resultList = constsSiteCarouselDao.queryCarousels(count);
        //处理为七牛图片链接
        for (ConstsSiteCarousel item : resultList) {
            item.setPicture(QiniuStorage.getUrl(item.getPicture()));
        }

        return resultList;
    }

    @Override
    public TailPage<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page) {
        Integer itemsTotalCount = constsSiteCarouselDao.getTotalItemsCount(queryEntity);
        List<ConstsSiteCarousel> items = constsSiteCarouselDao.queryPage(queryEntity, page);

        if (CollectionUtils.isNotEmpty(items)) {
            for (ConstsSiteCarousel item : items) {
                String pictureUrl = QiniuStorage.getUrl(item.getPicture());
                item.setPicture(pictureUrl);
            }
        }
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public void create(ConstsSiteCarousel entity) {
        constsSiteCarouselDao.create(entity);
    }

    @Override
    public void createSelectivity(ConstsSiteCarousel entity) {
        constsSiteCarouselDao.createSelectivity(entity);
    }

    @Override
    public void update(ConstsSiteCarousel entity) {
        constsSiteCarouselDao.update(entity);
    }

    @Override
    public void updateSelectivity(ConstsSiteCarousel entity) {
        constsSiteCarouselDao.updateSelectivity(entity);
    }

    @Override
    public void delete(ConstsSiteCarousel entity) {
        constsSiteCarouselDao.delete(entity);
    }

    @Override
    public void deleteLogic(ConstsSiteCarousel entity) {
        constsSiteCarouselDao.delteLogic(entity);
    }

}
