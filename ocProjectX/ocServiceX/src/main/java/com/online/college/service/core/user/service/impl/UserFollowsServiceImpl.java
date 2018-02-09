package com.online.college.service.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.dao.UserFollowsDao;
import com.online.college.service.core.user.domain.UserFollowStudyRecord;
import com.online.college.service.core.user.domain.UserFollows;
import com.online.college.service.core.user.service.IUserFollowsService;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-09 10:01
* @version V1.0
 */
@Service
public class UserFollowsServiceImpl implements IUserFollowsService {

    @Autowired
    private UserFollowsDao userFollowsDao;

    @Override
    public UserFollows getById(Long id) {
        return userFollowsDao.getById(id);
    }

    @Override
    public List<UserFollows> queryAll(UserFollows queryEntity) {
        return userFollowsDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<UserFollows> queryPage(UserFollows queryEntity, TailPage<UserFollows> page) {
        Integer itemsTotalCount = userFollowsDao.getTotalItemsCount(queryEntity);
        List<UserFollows> items = userFollowsDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity,
            TailPage<UserFollowStudyRecord> page) {
        Integer itemsTotalCount = userFollowsDao.getFollowStudyRecordCount(queryEntity);
        List<UserFollowStudyRecord> items = userFollowsDao.queryFollowStudyRecord(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public void createSelectivity(UserFollows entity) {
        userFollowsDao.createSelectivity(entity);
    }

    @Override
    public void update(UserFollows entity) {
        userFollowsDao.update(entity);
    }

    @Override
    public void updateSelectivity(UserFollows entity) {
        userFollowsDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserFollows entity) {
        userFollowsDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserFollows entity) {
        userFollowsDao.deleteLogic(entity);
    }

}
