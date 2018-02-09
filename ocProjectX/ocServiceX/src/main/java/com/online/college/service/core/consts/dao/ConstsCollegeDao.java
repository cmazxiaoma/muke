package com.online.college.service.core.consts.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.domain.ConstsCollege;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-08 14:47
* @version V1.0
 */
public interface ConstsCollegeDao {

    public ConstsCollege getById(Long id);

    public ConstsCollege getByCode(String code);

    public List<ConstsCollege> queryAll(ConstsCollege queryEntity);

    public Integer getTotalItemsCount(ConstsCollege queryEntity);

    public List<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page);

    public void create(ConstsCollege entity);

    public void createSelectivity(ConstsCollege entity);

    public void updateSelectivity(ConstsCollege entity);

    public void update(ConstsCollege entity);

    public void delete(ConstsCollege entity);

    public void deleteLogic(ConstsCollege entity);

}
