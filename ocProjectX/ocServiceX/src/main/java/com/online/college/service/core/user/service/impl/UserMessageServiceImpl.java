package com.online.college.service.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.dao.UserMessageDao;
import com.online.college.service.core.user.domain.UserMessage;
import com.online.college.service.core.user.service.IUserMessageService;

@Service
public class UserMessageServiceImpl implements IUserMessageService {

    @Autowired
    private UserMessageDao entityDao;

    @Override
    public UserMessage getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<UserMessage> queryAll(UserMessage queryEntity) {
        return entityDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<UserMessage> queryPage(UserMessage queryEntity, TailPage<UserMessage> page) {
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<UserMessage> items = entityDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public void create(UserMessage entity) {
        entityDao.create(entity);
    }

    @Override
    public void update(UserMessage entity) {
        entityDao.update(entity);
    }

    @Override
    public void updateSelectivity(UserMessage entity) {
        entityDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserMessage entity) {
        entityDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserMessage entity) {
        entityDao.deleteLogic(entity);
    }

}
