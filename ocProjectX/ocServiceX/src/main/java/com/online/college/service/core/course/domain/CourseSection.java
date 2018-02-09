package com.online.college.service.core.course.domain;

import com.online.college.common.orm.BaseEntity;

/**
 *
* @Description: 课程章节
* @author cmazxiaoma
* @date 2018-02-09 11:00
* @version V1.0
 */
public class CourseSection extends BaseEntity {

    private static final long serialVersionUID = 438929401755578424L;

    /**
     * 归属课程id
     */
    private Long courseId;

    /**
     * 父章节id, 只有2级
     */
    private Long parentId;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 时长
     */
    private String time;

    /**
     * 未上架(0), 上架(1)
     */
    private Integer onsale;

    /**
     * 视频url
     */
    private String videoUrl;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getOnsale() {
        return onsale;
    }

    public void setOnsale(Integer onsale) {
        this.onsale = onsale;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
