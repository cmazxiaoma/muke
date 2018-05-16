package com.online.college.service.core.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.course.dao.CourseSectionDao;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;
import com.online.college.service.core.course.service.ICourseService;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-09 13:44
 * @version V1.0
 */
@Service
public class CourseSectionServiceImpl implements ICourseSectionService {

    @Autowired
    private CourseSectionDao entityDao;

    @Autowired
    private ICourseService courseService;

    @Override
    public CourseSection getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<CourseSection> queryByParentId(CourseSection queryEntity) {
        return entityDao.queryByParentId(queryEntity);
    }

    private String getCourseDuration(String courseTotalTime) {
        String result;
        String[] timeArr = courseTotalTime.split(":");

        Integer second = Integer.parseInt(timeArr[0]) * 60
                + Integer.parseInt(timeArr[1]);

        Integer minute = second / 60;

        Integer hour = minute / 60;

        if (hour > 0) {
            minute = minute % 60;
            result = hour + "小时" + minute + "分钟";
        } else {
            result = minute + "分钟";
        }

        return result;
    }

    private String appendCourseSectionTime(String time1, String time2) {
        String[] time1Arr = time1.split(":");
        String[] time2Arr = time2.split(":");

        Integer second1 = Integer.parseInt(time1Arr[0]) * 60 + Integer.parseInt(time1Arr[1]);
        Integer second2 = Integer.parseInt(time2Arr[0]) * 60 + Integer.parseInt(time2Arr[1]);

        Integer secondTotal = second1 + second2;

        Integer minute = secondTotal / 60;
        String minuteStr = minute + "";

        if (minute < 10) {
            minuteStr = "0" + minute;
        }

        Integer secode = secondTotal % 60;
        String secodeStr = secode + "";

        if (secode < 10) {
            secodeStr = "0" + secode;
        }
        return minuteStr + ":" + secodeStr;
    }

    private void updateCourseSectionTime(CourseSection courseSection) {
        Long courseId = courseSection.getCourseId();
        Long parentCourseSectionId = courseSection.getParentId();

        if (courseId == null || parentCourseSectionId == null) {
            if (courseSection.getId() != null) {
                courseSection = getById(courseSection.getId());
                courseId = courseSection.getCourseId();
                parentCourseSectionId = courseSection.getParentId();
            }
        }

        String courseTotalTime = "00:00";

        String parentCourseSectiontotalTime = "00:00";

        CourseSection parentCourseSection = new CourseSection();
        parentCourseSection.setId(parentCourseSectionId);

        Course course = new Course();
        course.setId(courseId);

        //课程的章是无法更改视频时长，是通过章下面的所有节的视频时长加起来的
        if (Long.valueOf(0).equals(parentCourseSectionId)) {

        } else {
            CourseSection queryEntity = new CourseSection();
            queryEntity.setParentId(parentCourseSectionId);
            queryEntity.setCourseId(courseId);

            List<CourseSection> courseSectionList = queryByParentId(queryEntity);

            for (CourseSection item : courseSectionList) {
                //统计父章总时长
                parentCourseSectiontotalTime = appendCourseSectionTime(parentCourseSectiontotalTime,
                        item.getTime());
            }
            //更新父章的时长
            parentCourseSection.setTime(parentCourseSectiontotalTime);
            updateSelectivity(parentCourseSection);

            //统计所有章
            //先遍历出所有章
            queryEntity.setParentId(0L);
            List<CourseSection> mainCourseSectionList = queryByParentId(queryEntity);

            for (CourseSection item : mainCourseSectionList) {
                courseTotalTime = appendCourseSectionTime(courseTotalTime,
                        item.getTime());
            }
            courseTotalTime = getCourseDuration(courseTotalTime);

            //更新课程总时长
            course.setTime(courseTotalTime);
            courseService.updateSelectivity(course);
        }
    }

    @Override
    public List<CourseSection> queryAll(CourseSection queryEntity) {
        return entityDao.queryAll(queryEntity);
    }

    /**
     * 获取课程章最大的sort
     */
    @Override
    public Integer getMaxSort(Long courseId) {
        return entityDao.getMaxSort(courseId);
    }

    @Override
    public TailPage<CourseSection> queryPage(CourseSection queryEntity, TailPage<CourseSection> page) {
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<CourseSection> items = entityDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public void createSelectivity(CourseSection entity) {
        entityDao.createSelectivity(entity);
    }

    /**
     * 批量创建
     */
    @Override
    public void createList(List<CourseSection> entityList) {
        entityDao.createList(entityList);
    }

    @Override
    public void update(CourseSection entity) {
        entityDao.update(entity);
        this.updateCourseSectionTime(entity);
    }

    @Override
    public void updateSelectivity(CourseSection entity) {
        entityDao.updateSelectivity(entity);
        this.updateCourseSectionTime(entity);
    }

    @Override
    public void delete(CourseSection entity) {
        entityDao.delete(entity);
        this.updateCourseSectionTime(entity);
    }

    @Override
    public void deleteLogic(CourseSection entity) {
        entityDao.deleteLogic(entity);
        this.updateCourseSectionTime(entity);
    }

    /**
     * 比当前sort大的，正序排序的第一个
     *
     * @param curCourseSection
     * @return
     */
    @Override
    public CourseSection getSortSectionMax(CourseSection curCourseSection) {
        return entityDao.getSortSectionMax(curCourseSection);
    }

    /**
     * 比当前sort小的，倒序排序的第一个
     *
     * @param curCourseSection
     * @return
     */
    @Override
    public CourseSection getSortSectionMin(CourseSection curCourseSection) {
        return entityDao.getSortSectionMin(curCourseSection);
    }

}
