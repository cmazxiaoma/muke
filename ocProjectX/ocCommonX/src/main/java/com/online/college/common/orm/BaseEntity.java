package com.online.college.common.orm;

import java.util.Date;

/**
 *
* @Description: TODO
* @author majinlan
* @date 2018-02-26 13:40
* @version V1.0
 */
public class BaseEntity extends BaseLongModel {

    private static final long serialVersionUID = 2227850114499003693L;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer del = 0;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
