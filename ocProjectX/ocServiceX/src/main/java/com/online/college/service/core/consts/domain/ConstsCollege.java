package com.online.college.service.core.consts.domain;

import com.online.college.common.orm.BaseEntity;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-08 14:54
* @version V1.0
 */
public class ConstsCollege extends BaseEntity {

    private static final long serialVersionUID = 1963562806788886565L;

    private String name;

    private String code;

    private String picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
