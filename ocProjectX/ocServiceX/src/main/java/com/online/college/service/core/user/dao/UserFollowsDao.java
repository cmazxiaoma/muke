package com.online.college.service.core.user.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserFollowStudyRecord;
import com.online.college.service.core.user.domain.UserFollows;

/**
 *
* @Description: 用户关注
* @author cmazxiaoma
* @date 2018-02-08 17:27
* @version V1.0
 */
public interface UserFollowsDao {

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public UserFollows getById(Long id);

    /**
     * 获取所有
     * @param queryEntity
     * @return
     */
    public List<UserFollows> queryAll(UserFollows queryEntity);

    /**
     * 获取总数量
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(UserFollows queryEntity);

    /**
     * 分页获取
     * @param queryEntity
     * @param page
     * @return
     */
    public List<UserFollows> queryPage(UserFollows queryEntity , TailPage<UserFollows> page);

    /**
     * 获取总数量
     * @param queryEntity
     * @return
     */
    public Integer getFollowStudyRecordCount(UserFollowStudyRecord queryEntity);

    /**
     * 分页获取
     * @param queryEntity
     * @param page
     * @return
     */
    public List<UserFollowStudyRecord> queryFollowStudyRecord(UserFollowStudyRecord queryEntity , TailPage<UserFollowStudyRecord> page);

    /**
     * 创建新记录
     * @param entity
     */
    public void createSelectivity(UserFollows entity);

    /**
     * 根据id更新
     * @param entity
     */
    public void update(UserFollows entity);

    /**
     * 根据id选择性更新自动
     * @param entity
     */
    public void updateSelectivity(UserFollows entity);

    /**
     * 物理删除
     * @param entity
     */
    public void delete(UserFollows entity);

    /**
     * 逻辑删除
     * @param entity
     */
    public void deleteLogic(UserFollows entity);

}

