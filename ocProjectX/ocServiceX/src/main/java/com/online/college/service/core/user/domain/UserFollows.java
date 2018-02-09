package com.online.college.service.core.user.domain;

import com.online.college.common.orm.BaseEntity;

/**
 *
* @Description: 用户关注
* @author cmazxiaoma
* @date 2018-02-08 16:51
* @version V1.0
 */
public class UserFollows extends BaseEntity {

    private static final long serialVersionUID = 2803626207709773574L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关注的用户id
     */
    private Long followId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

}
