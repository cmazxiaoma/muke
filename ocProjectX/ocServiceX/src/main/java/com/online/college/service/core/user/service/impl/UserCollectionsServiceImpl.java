package com.online.college.service.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.dao.UserCollectionsDao;
import com.online.college.service.core.user.domain.UserCollections;
import com.online.college.service.core.user.service.IUserCollectionsService;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-09 09:50
 * @version V1.0
 */
@Service
public class UserCollectionsServiceImpl implements IUserCollectionsService {

    @Autowired
    private UserCollectionsDao userCollectionsDao;

    @Override
    public UserCollections getById(Long id) {
        return userCollectionsDao.getById(id);
    }

    @Override
    public List<UserCollections> queryAll(UserCollections queryEntity) {
        return userCollectionsDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<UserCollections> queryPage(UserCollections queryEntity, TailPage<UserCollections> page) {
        Integer itemsTotalCount = userCollectionsDao.getTotalItemsCount(queryEntity);
        List<UserCollections> items = userCollectionsDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public void createSelectivity(UserCollections entity) {
        userCollectionsDao.createSelectivity(entity);
    }

    @Override
    public void update(UserCollections entity) {
        userCollectionsDao.update(entity);
    }

    @Override
    public void updateSelectivity(UserCollections entity) {
        userCollectionsDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserCollections entity) {
        userCollectionsDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserCollections entity) {
        userCollectionsDao.deleteLogic(entity);
    }

}
