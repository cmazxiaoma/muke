package com.online.college.opt.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.service.core.course.domain.CourseSection;

/**
 * 课程章节
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-11 19:11
* @version V1.0
 */
public class CourseSectionVO extends CourseSection {

    private static final long serialVersionUID = 180753077428934254L;

    //小节
    private List<CourseSection> sections = new ArrayList<CourseSection>();


    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }

}
