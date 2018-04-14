package com.online.college.service.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserFollowStudyRecord;
import com.online.college.service.core.user.domain.UserFollows;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-08 18:23
 * @version V1.0
 */
public interface IUserFollowsService {

    public UserFollows getById(Long id);

    public List<UserFollows> queryAll(UserFollows queryEntity);

    public TailPage<UserFollows> queryPage(UserFollows queryEntity, TailPage<UserFollows> page);

    public TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity,
            TailPage<UserFollowStudyRecord> page);

    public void createSelectivity(UserFollows entity);

    public void update(UserFollows entity);

    public void updateSelectivity(UserFollows entity);

    public void delete(UserFollows entity);

    public void deleteLogic(UserFollows entity);

}
