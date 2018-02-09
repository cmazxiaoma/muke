package com.online.college.service.core.user.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserMessage;

/**
 *
* @Description: 用户消息
* @author cmazxiaoma
* @date 2018-02-08 17:29
* @version V1.0
 */
public interface UserMessageDao {

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public UserMessage getById(Long id);

    /**
     * 获取所有
     * @param queryEntity
     * @return
     */
    public List<UserMessage> queryAll(UserMessage queryEntity);

    /**
     * 获取总数量
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(UserMessage queryEntity);

    /**
     * 分页获取
     * @param queryEntity
     * @param page
     * @return
     */
    public List<UserMessage> queryPage(UserMessage queryEntity , TailPage<UserMessage> page);

    /**
     * 创建新记录
     * @param entity
     */
    public void create(UserMessage entity);

    /**
     * 根据id更新
     * @param entity
     */
    public void update(UserMessage entity);

    /**
     * 根据id选择性更新自动
     * @param entity
     */
    public void updateSelectivity(UserMessage entity);

    /**
     * 物理删除
     * @param entity
     */
    public void delete(UserMessage entity);

    /**
     * 逻辑删除
     * @param entity
     */
    public void deleteLogic(UserMessage entity);

}
