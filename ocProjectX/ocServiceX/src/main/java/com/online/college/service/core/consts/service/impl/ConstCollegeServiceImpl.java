package com.online.college.service.core.consts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.consts.dao.ConstsCollegeDao;
import com.online.college.service.core.consts.domain.ConstsCollege;
import com.online.college.service.core.consts.service.IConstsCollegeService;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 15:35
 * @version V1.0
 */
@Service
public class ConstCollegeServiceImpl implements IConstsCollegeService {

    @Autowired
    private ConstsCollegeDao entityDao;

    @Override
    public ConstsCollege getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public ConstsCollege getByCode(String code) {
        return entityDao.getByCode(code);
    }

    @Override
    public List<ConstsCollege> queryAll(ConstsCollege queryEntity) {
        return entityDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page) {
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<ConstsCollege> items = entityDao.queryPage(queryEntity, page);
        page.setItems(items);
        page.setItemsTotalCount(itemsTotalCount);

        return page;
    }

    @Override
    public void create(ConstsCollege entity) {
        entityDao.create(entity);
    }

    @Override
    public void createSelectivity(ConstsCollege entity) {
        entityDao.createSelectivity(entity);
    }

    @Override
    public void update(ConstsCollege entity) {
        entityDao.update(entity);
    }

    @Override
    public void updateSelectivity(ConstsCollege entity) {
        entityDao.updateSelectivity(entity);
    }

    @Override
    public void delete(ConstsCollege entity) {
        entityDao.delete(entity);
    }

    @Override
    public void deleteLogic(ConstsCollege entity) {
        entityDao.deleteLogic(entity);
    }

}
