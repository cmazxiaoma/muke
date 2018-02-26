package com.online.college.service.core.consts.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.domain.ConstsCollege;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-08 15:23
 * @version V1.0
 */
public interface IConstsCollegeService {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public ConstsCollege getById(Long id);

    /**
     * 根据code获取
     *
     * @param code
     * @return
     */
    public ConstsCollege getByCode(String code);

    /**
     * 获取所有
     *
     * @param queryEntity
     * @return
     */
    public List<ConstsCollege> queryAll(ConstsCollege queryEntity);

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     * @return
     */
    public TailPage<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page);

    /**
     * 创建
     *
     * @param entity
     */
    public void create(ConstsCollege entity);

    /**
     * 创建网校
     *
     * @param entity
     */
    public void createSelectivity(ConstsCollege entity);

    /**
     * 根据id更新
     *
     * @param entity
     */
    public void update(ConstsCollege entity);

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    public void updateSelectivity(ConstsCollege entity);

    /**
     * 物理删除
     *
     * @param entity
     */
    public void delete(ConstsCollege entity);

    /**
     * 逻辑删除
     *
     * @param entity
     */
    public void deleteLogic(ConstsCollege entity);
}
