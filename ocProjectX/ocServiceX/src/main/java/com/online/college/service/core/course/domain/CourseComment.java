package com.online.college.service.core.course.domain;

import com.online.college.common.orm.BaseEntity;

/**
 *
 * @Description: 课程评论&OA
 * @author majinlan
 * @date 2018-02-09 10:49
 * @version V1.0
 */
public class CourseComment extends BaseEntity {

    private static final long serialVersionUID = -500081448009815369L;

    /**
     * 用户userName
     */
    private String username;

    /**
     * 评论对象userName
     */
    private String toUsername;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 章节id
     */
    private Long sectionId;

    /**
     * 章节标题
     */
    private String sectionTitle;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 引用id
     */
    private Long refId;

    /**
     * 引用内容
     */
    private String refContent;

    /**
     * 类型： 0-评论, 1-答疑QA
     */
    private Integer type;

    /**
     * 用户头像
     */
    private String header;

    /**
     * 课程名称
     */
    private String courseName;

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

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRefContent() {
        return refContent;
    }

    public void setRefContent(String refContent) {
        this.refContent = refContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

}
