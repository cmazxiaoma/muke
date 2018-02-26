package com.online.college.service.core.user.domain;

import java.util.Date;

/**
 *
 * @Description: 关注的用户学习记录DTO
 * @author cmazxiaoma
 * @date 2018-02-08 17:08
 * @version V1.0
 */
public class UserFollowStudyRecord {

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 章节id
     */
    private Long sectionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户头像
     */
    private String header;

    /**
     * 关注用户id
     */
    private Long followId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 章节名称
     */
    private String sectionName;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
