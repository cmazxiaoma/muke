package com.online.college.service.core.user.domain;

import com.online.college.common.orm.BaseEntity;

/**
 *
 * @Description: 用户收藏
 * @author cmazxiaoma
 * @date 2018-02-08 16:43
 * @version V1.0
 */
public class UserCollections extends BaseEntity {

    private static final long serialVersionUID = -4373978482453404031L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户收藏分类
     */
    private Integer classify;

    /**
     * 用户收藏id
     */
    private Long objectId;

    /**
     * 用户收藏备注
     */
    private String tips;

    /**
     * 收藏名称
     */
    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
