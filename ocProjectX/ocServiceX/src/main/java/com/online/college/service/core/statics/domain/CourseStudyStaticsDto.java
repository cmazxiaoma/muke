package com.online.college.service.core.statics.domain;

import java.util.Date;

/**
 *
 * @Description: 课程学习统计
 * @author majinlan
 * @date 2018-02-08 16:13
 * @version V1.0
 */
public class CourseStudyStaticsDto {

    private Integer totalCount;

    private String dateStr;

    private Date startDate;

    private Date endDate;

    private Long courseId;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

}
