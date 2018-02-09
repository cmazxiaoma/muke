package com.online.college.service.core.user.domain;

import com.online.college.common.orm.BaseEntity;

/**
 *
* @Description: 用户学习课程DTO
* @author cmazxiaoma
* @date 2018-02-08 17:01
* @version V1.0
 */
public class UserCourseSectionDto extends BaseEntity {

    private static final long serialVersionUID = -3378797759981873499L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 章节名
     */
    private String sectionName;

    /**
     * 用户头像
     */
    private String header;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
