package com.online.college.service.core.course.domain;

import org.apache.commons.lang.StringUtils;

import com.online.college.common.util.BeanUtil;

/**
 *
* @Description: 课程查询实体类
* @author cmazxiaoma
* @date 2018-02-09 11:05
* @version V1.0
 */
public class CourseQueryDto extends Course {

    private static final long serialVersionUID = -1559715287444682950L;

    private String sortField;

    private String sortDirection = "DESC";

    /**
     * limit开始
     */
    private Integer start = 0;

    /**
     * 数量
     */
    private Integer count;

    /**
     * limit结束
     */
    private Integer end;

    /**
     * 按照sortField升序
     * @param sortField
     */
    public void ascSortField(String sortField) {
        if (StringUtils.isNotEmpty(sortField)) {
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = "ASC";
        }
    }

    /**
     * 按照sortField降序
     * @param sortField
     */
    public void descSortField(String sortField) {
        if (StringUtils.isNotEmpty(sortField)) {
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = "DESC";
        }
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public Integer getEnd() {
        if (null != this.count) {
            if (null == this.start) {
                this.start = 0;
            }
            this.end = this.start + this.count;
        }
        return end;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSortField() {
        return sortField;
    }

}
