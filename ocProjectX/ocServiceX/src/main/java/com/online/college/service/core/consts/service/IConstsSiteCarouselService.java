package com.online.college.service.core.consts.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.domain.ConstsSiteCarousel;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-08 15:24
 * @version V1.0
 */
public interface IConstsSiteCarouselService {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public ConstsSiteCarousel getById(Long id);

    /**
     * 获取所有
     *
     * @param count
     * @return
     */
    public List<ConstsSiteCarousel> queryCarousels(Integer count);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public TailPage<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page);

    /**
     * 创建
     *
     * @param entity
     */
    public void create(ConstsSiteCarousel entity);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void createSelectivity(ConstsSiteCarousel entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(ConstsSiteCarousel entity);

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    public void updateSelectivity(ConstsSiteCarousel entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(ConstsSiteCarousel entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(ConstsSiteCarousel entity);
}
