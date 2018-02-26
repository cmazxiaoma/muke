package com.online.college.portal.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.service.core.course.domain.CourseSection;

/**
 *
 * @Description: 课程章节
 * @author cmazxiaoma
 * @date 2018-02-09 14:05
 * @version V1.0
 */
public class CourseSectionVO extends CourseSection {

    private static final long serialVersionUID = -1606027532366025832L;

    // 小节
    private List<CourseSection> sections = new ArrayList<CourseSection>();

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }

}
