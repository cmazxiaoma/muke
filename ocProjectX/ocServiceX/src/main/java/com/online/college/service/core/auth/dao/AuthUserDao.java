package com.online.college.service.core.auth.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.auth.domain.AuthUser;

public interface AuthUserDao {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public AuthUser getById(Long id);

    /**
     * 根据用户名
     *
     * @param userName
     * @return
     */
    public AuthUser getByUsername(String username);

    /**
     * 根据用户名和密码
     *
     * @param authUser
     * @return
     */
    public AuthUser getByUsernameAndPassword(AuthUser authUser);

    /**
     * 获取首页推荐5个讲师
     *
     * @return
     */
    public List<AuthUser> queryRecomd();

    /**
     * 获取总数量
     *
     * @param queryEntity
     * @return
     */
    public Integer getTotalItemsCount(AuthUser queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public List<AuthUser> queryPage(AuthUser queryEntity, TailPage<AuthUser> page);

    /**
     * 创建新记录
     *
     * @param entity
     */
    public void createSelectivity(AuthUser entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(AuthUser entity);

    /**
     * 根据id选择性更新自动
     *
     * @param entity
     */
    public void updateSelectivity(AuthUser entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(AuthUser entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(AuthUser entity);
}
