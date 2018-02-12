package com.online.college.wechat.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.service.core.course.domain.CourseSection;

public class CourseSectionVO extends CourseSection {

    private static final long serialVersionUID = -5970329431719358646L;

    /**
     * 小节
     */
    private List<CourseSection> sections = new ArrayList<CourseSection>();

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }

}
