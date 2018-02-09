package com.online.college.service.core.course.domain;

import java.math.BigDecimal;

import com.online.college.common.orm.BaseEntity;

/**
 *
* @Description: 课程
* @author cmazxiaoma
* @date 2018-02-09 10:32
* @version V1.0
 */
public class Course extends BaseEntity {

    private static final long serialVersionUID = -3042972655462643063L;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程类型
     */
    private String type;

    /**
     * 课程分类
     */
    private String classify;


    /**
     * 课程分类名称
     */
    private String classifyName;

    /**
     * 课程二级分类
     */
    private String subClassify;

    /**
     * 课程二级分类名称
     */
    private String subClassifyName;

    /**
     * 课程方向
     */
    private String direction;

    /**
     * 归属人
     */
    private String username;

    /**
     * 课程级别: 1-初级  2-中级 3-高级
     */
    private Integer level;

    /**
     * 是否免费  0-否  1-是
     */
    private Integer free;

    /**
     * 课程价格
     */
    private BigDecimal price;

    /**
     * 时长
     */
    private String time;

    /**
     * 未上架(0), 上架(1)
     */
    private Integer onsale;

    /**
     * 课程描述
     */
    private String brief;

    /**
     * 课程图片
     */
    private String picture;

    /**
     * 未推荐(0), 推荐(1)
     */
    private Integer recommend;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 学习人数
     */
    private Integer studyCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getSubClassify() {
        return subClassify;
    }

    public void setSubClassify(String subClassify) {
        this.subClassify = subClassify;
    }

    public String getSubClassifyName() {
        return subClassifyName;
    }

    public void setSubClassifyName(String subClassifyName) {
        this.subClassifyName = subClassifyName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(Integer studyCount) {
        this.studyCount = studyCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
