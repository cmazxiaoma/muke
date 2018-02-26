package com.online.college.portal.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.service.core.consts.domain.ConstsClassify;
import com.online.college.service.core.course.domain.Course;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-09 14:05
 * @version V1.0
 */
public class ConstsClassifyVO extends ConstsClassify {

    private static final long serialVersionUID = -1240452757085909248L;

    /**
     * 子分类列表
     */
    private List<ConstsClassify> subClassifyList = new ArrayList<>();

    /**
     * 课程推荐列表
     */
    private List<Course> recomdCourseList;

    public List<ConstsClassify> getSubClassifyList() {
        return subClassifyList;
    }

    public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
        this.subClassifyList = subClassifyList;
    }

    public List<Course> getRecomdCourseList() {
        return recomdCourseList;
    }

    public void setRecomdCourseList(List<Course> recomdCourseList) {
        this.recomdCourseList = recomdCourseList;
    }

}
