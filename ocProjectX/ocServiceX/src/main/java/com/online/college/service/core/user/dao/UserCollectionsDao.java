package com.online.college.service.core.user.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserCollections;

/**
 *
 * @Description: 用户收藏
 * @author majinlan
 * @date 2018-02-08 17:21
 * @version V1.0
 */
public interface UserCollectionsDao {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public UserCollections getById(Long id);

    /**
     * 获取所有
     *
     * @param queryEntity
     * @return
     */
    public List<UserCollections> queryAll(UserCollections queryEntity);

    /**
     * 获取总数量
     *
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(UserCollections queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public List<UserCollections> queryPage(UserCollections queryEntity, TailPage<UserCollections> page);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void create(UserCollections entity);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void createSelectivity(UserCollections entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(UserCollections entity);

    /**
     * 根据id选择性更新自动
     *
     * @param entity
     */
    public void updateSelectivity(UserCollections entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(UserCollections entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(UserCollections entity);

}
