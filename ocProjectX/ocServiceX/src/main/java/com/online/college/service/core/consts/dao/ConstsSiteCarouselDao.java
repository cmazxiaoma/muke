package com.online.college.service.core.consts.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.domain.ConstsSiteCarousel;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-08 14:48
 * @version V1.0
 */
public interface ConstsSiteCarouselDao {

    public ConstsSiteCarousel getById(Long id);

    public List<ConstsSiteCarousel> queryCarousels(Integer count);

    public List<ConstsSiteCarousel> queryAll(ConstsSiteCarousel queryEntity);

    public Integer getTotalItemsCount(ConstsSiteCarousel queryEntity);

    public List<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page);

    public void create(ConstsSiteCarousel entity);

    public void createSelectivity(ConstsSiteCarousel entity);

    public void update(ConstsSiteCarousel entity);

    public void updateSelectivity(ConstsSiteCarousel entity);

    public void delete(ConstsSiteCarousel entity);

    public void delteLogic(ConstsSiteCarousel entity);

}
