package com.online.college.service.core.consts.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.domain.ConstsClassify;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 15:23
 * @version V1.0
 */
public interface IConstsClassifyService {

    public ConstsClassify getById(Long id);

    public List<ConstsClassify> queryAll();

    public ConstsClassify getByCode(String code);

    public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

    public TailPage<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page);

    public void create(ConstsClassify entity);

    public void createSelectivity(ConstsClassify entity);

    public void update(ConstsClassify entity);

    public void updateSelectivity(ConstsClassify entity);

    public void delete(ConstsClassify entity);

    public void deleteLogic(ConstsClassify entity);

}
