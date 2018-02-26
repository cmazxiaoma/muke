package com.online.college.service.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.domain.UserMessage;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-08 18:23
 * @version V1.0
 */
public interface IUserMessageService {

    public UserMessage getById(Long id);

    public List<UserMessage> queryAll(UserMessage queryEntity);

    public TailPage<UserMessage> queryPage(UserMessage queryEntity, TailPage<UserMessage> page);

    public void create(UserMessage entity);

    public void update(UserMessage entity);

    public void updateSelectivity(UserMessage entity);

    public void delete(UserMessage entity);

    public void deleteLogic(UserMessage entity);

}
