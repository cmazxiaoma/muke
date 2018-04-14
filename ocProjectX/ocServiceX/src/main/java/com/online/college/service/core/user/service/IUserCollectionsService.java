package com.online.college.service.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserCollections;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 18:23
 * @version V1.0
 */
public interface IUserCollectionsService {

    public UserCollections getById(Long id);

    public List<UserCollections> queryAll(UserCollections queryEntity);

    public TailPage<UserCollections> queryPage(UserCollections queryEntity, TailPage<UserCollections> page);

    public void createSelectivity(UserCollections entity);

    public void update(UserCollections entity);

    public void updateSelectivity(UserCollections entity);

    public void delete(UserCollections entity);

    public void deleteLogic(UserCollections entity);
}
