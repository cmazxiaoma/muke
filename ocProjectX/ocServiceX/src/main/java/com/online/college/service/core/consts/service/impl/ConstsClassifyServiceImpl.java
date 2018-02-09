package com.online.college.service.core.consts.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.dao.ConstsClassifyDao;
import com.online.college.service.core.consts.domain.ConstsClassify;
import com.online.college.service.core.consts.service.IConstsClassifyService;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-08 15:35
* @version V1.0
 */
@Service
public class ConstsClassifyServiceImpl implements IConstsClassifyService {

    @Autowired
    private ConstsClassifyDao entityDao;

    @Override
    public ConstsClassify getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<ConstsClassify> queryAll() {
        return entityDao.queryAll();
    }

    @Override
    public ConstsClassify getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        ConstsClassify constsClassify = new ConstsClassify();
        constsClassify.setCode(code);

        List<ConstsClassify> list = entityDao.queryByCondition(constsClassify);

        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity) {
        return entityDao.queryByCondition(queryEntity);
    }

    @Override
    public TailPage<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page) {
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<ConstsClassify> items = entityDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public void create(ConstsClassify entity) {
        entityDao.create(entity);
    }

    @Override
    public void createSelectivity(ConstsClassify entity) {
        entityDao.createSelectivity(entity);
    }

    @Override
    public void update(ConstsClassify entity) {
        entityDao.update(entity);
    }

    @Override
    public void updateSelectivity(ConstsClassify entity) {
        entityDao.updateSelectivity(entity);
    }

    @Override
    public void delete(ConstsClassify entity) {
        entityDao.delete(entity);
    }

    @Override
    public void deleteLogic(ConstsClassify entity) {
        entityDao.deleteLogic(entity);
    }

}
