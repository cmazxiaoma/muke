package com.online.college.service.core.consts.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.domain.ConstsClassify;
import com.online.college.service.core.consts.domain.ConstsCollege;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 14:46
 * @version V1.0
 */
public interface ConstsClassifyDao {

    public ConstsClassify getById(Long id);

    public ConstsCollege getByCode(String code);

    public List<ConstsClassify> queryAll();

    public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

    public Integer getTotalItemsCount(ConstsClassify queryEntity);

    public List<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page);

    public void create(ConstsClassify entity);

    public void createSelectivity(ConstsClassify entity);

    public void update(ConstsClassify entity);

    public void updateSelectivity(ConstsClassify entity);

    public void delete(ConstsClassify entity);

    public void deleteLogic(ConstsClassify entity);

}
