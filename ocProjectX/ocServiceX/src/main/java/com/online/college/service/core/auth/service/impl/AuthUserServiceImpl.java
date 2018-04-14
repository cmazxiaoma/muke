package com.online.college.service.core.auth.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.service.core.auth.dao.AuthUserDao;
import com.online.college.service.core.auth.domain.AuthUser;
import com.online.college.service.core.auth.service.IAuthUserService;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 13:58
 * @version V1.0
 */
@Service
public class AuthUserServiceImpl implements IAuthUserService {

    @Autowired
    private AuthUserDao authUserDao;

    @Override
    public AuthUser getById(Long id) {
        return authUserDao.getById(id);
    }

    @Override
    public AuthUser getByUsername(String username) {
        return authUserDao.getByUsername(username);
    }

    @Override
    public AuthUser getByUsernameAndPassword(AuthUser authUser) {
        return authUserDao.getByUsernameAndPassword(authUser);
    }

    @Override
    public List<AuthUser> queryRecomd() {
        List<AuthUser> recomdList = authUserDao.queryRecomd();

        if (CollectionUtils.isNotEmpty(recomdList)) {
            for (AuthUser item : recomdList) {
                if (StringUtils.isNotEmpty(item.getHeader())) {
                    item.setHeader(QiniuStorage.getUrl(item.getHeader()));
                }
            }
        }

        return recomdList;
    }

    @Override
    public TailPage<AuthUser> queryPage(AuthUser queryEntity, TailPage<AuthUser> page) {
        Integer itemsTotalCount = authUserDao.getTotalItemsCount(queryEntity);
        List<AuthUser> items = authUserDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public void createSelectivity(AuthUser entity) {
        authUserDao.createSelectivity(entity);
    }

    @Override
    public void update(AuthUser entity) {
        authUserDao.update(entity);
    }

    @Override
    public void updateSelectivity(AuthUser entity) {
        authUserDao.updateSelectivity(entity);
    }

    @Override
    public void delete(AuthUser entity) {
        authUserDao.delete(entity);
    }

    @Override
    public void deleteLogic(AuthUser entity) {
        authUserDao.deleteLogic(entity);
    }
}
