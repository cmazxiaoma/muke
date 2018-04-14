package com.online.college.service.core.consts;

/**
 *
 * @Description: 课程使用的枚举
 * @author majinlan
 * @date 2018-02-08 14:36
 * @version V1.0
 */
public enum CourseEnum {

    // 免费
    FREE(1),

    // 收费
    FREE_NOT(0),

    // 上架
    ONSALE(1),

    // 下架
    ONSALE_NOT(0),

    // 课程收藏
    COLLECTION_CLASSIFY_COURSE(1);

    private Integer value;

    private CourseEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
