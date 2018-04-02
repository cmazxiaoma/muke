package com.online.college.opt.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.online.college.common.web.SessionContext;
import com.online.college.opt.business.ICourseSectionBusiness;
import com.online.college.opt.vo.CourseSectionVO;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;
import com.online.college.service.core.course.service.ICourseService;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-11 19:33
 * @version V1.0
 */
@Service
public class CourseSectionBusinessImpl implements ICourseSectionBusiness {

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private ICourseService courseService;

    private static Pattern pattern =  Pattern.compile("^([0-5][0-9]):([0-5][0-9])$");

    /**
     * 批量添加
     */
    @Override
    public void batchAdd(List<CourseSectionVO> courseSections) {
        String courseTotalTime = "00:00";
        if (CollectionUtils.isNotEmpty(courseSections)) {
            // 先获取最大的排序id
            Integer maxSort = courseSectionService
                    .getMaxSort(courseSections.get(0).getCourseId());

            for (int i = 0; i < courseSections.size(); i++) {
                CourseSectionVO tmpVO = courseSections.get(i);

                if (null == maxSort) {
                    maxSort = 0;
                }

                maxSort += (i + 1);
                CourseSection courseSection = new CourseSection();
                courseSection.setCourseId(tmpVO.getCourseId());
                courseSection.setName(tmpVO.getName());

                // 大章的parentId默认为0
                courseSection.setParentId(0L);
                courseSection.setSort(maxSort);
                courseSection.setOnsale(CourseEnum.ONSALE.value());
                courseSection.setCreateTime(new Date());
                courseSection.setUpdateTime(new Date());
                courseSection.setCreateUser(SessionContext.getUsername());
                courseSection.setUpdateUser(SessionContext.getUsername());

                // 创建大章
                courseSectionService.createSelectivity(courseSection);

                List<CourseSection> subCourseSections = tmpVO.getSections();

                if (CollectionUtils.isNotEmpty(subCourseSections)) {
                    String mainCourseSectiontotalTime = "00:00";

                    for (int j = 0; j < subCourseSections.size(); j++) {
                        CourseSection courseSectionTmp = subCourseSections.get(j);
                        courseSectionTmp.setCourseId(courseSection.getCourseId());
                        courseSectionTmp.setParentId(courseSection.getId());
                        courseSectionTmp.setSort(j + 1);

                        courseSectionTmp.setCreateTime(new Date());
                        courseSectionTmp.setCreateUser(SessionContext.getUsername());
                        courseSectionTmp.setUpdateTime(new Date());
                        courseSectionTmp.setUpdateUser(SessionContext.getUsername());
                        courseSectionTmp.setOnsale(CourseEnum.ONSALE.value());

                        // 正则表达式匹配不成功
                        if (!pattern.matcher(courseSectionTmp.getTime()).find()) {
                            courseSectionTmp.setTime("00:00");
                        }

                        if (null == courseSectionTmp.getVideoUrl()) {
                            courseSectionTmp.setVideoUrl("");
                        }

                        // 计算上传 一个大章下所有的小节 总时间
                        mainCourseSectiontotalTime = appendCourseSectionTime(mainCourseSectiontotalTime,
                                        courseSectionTmp.getTime());
                    }
                    // 创建小节
                    courseSectionService.createList(subCourseSections);

                    // 更新大章的时间
                    courseSection.setTime(mainCourseSectiontotalTime);
                    courseSectionService.updateSelectivity(courseSection);

                    //更新课程的时间
                    courseTotalTime = appendCourseSectionTime(courseTotalTime,
                            mainCourseSectiontotalTime);
                }
            }

            courseTotalTime = getCourseDuration(courseTotalTime);
            Course course = new Course();
            course.setUpdateUser(SessionContext.getUsername());
            course.setUpdateTime(new Date());
            course.setTime(courseTotalTime);
            course.setId(courseSections.get(0).getCourseId());

            //更新course
            courseService.updateSelectivity(course);
        }
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

    /**
     * 批量导入
     */
    @Override
    public void batchImport(Long courseId, List<List<Object>> dataList) {
        // TODO Auto-generated method stub
        int count = dataList.size();

        List<CourseSectionVO> courseSections = new ArrayList<CourseSectionVO>();

        Map<String, CourseSectionVO> mainCourseSectionMap = Maps.newLinkedHashMap();

        for (int i = 0; i < count; i++) {
            List<Object> childList = dataList.get(i);
            // 章标题
            String title = childList.get(0) == null ? null : String.valueOf(childList.get(0));

            // 节标题
            String subTitle = childList.get(1) == null ? null : String.valueOf(childList.get(1));

            // 节视频url
            String url = childList.get(2) == null ? null : String.valueOf(childList.get(2));

            //节时长
            String time = childList.get(3) == null ? "00:00": String.valueOf(childList.get(3));

            //先判断大章是否在mainCourseSection存在
            CourseSectionVO courseSectionVO = new CourseSectionVO();

            if (mainCourseSectionMap.containsKey(title)) {
                courseSectionVO = mainCourseSectionMap.get(title);
            } else {
                courseSectionVO.setCourseId(courseId);
                courseSectionVO.setName(title);
            }

            mainCourseSectionMap.put(title, courseSectionVO);

            // 小节
            CourseSection subCourseSection = new CourseSection();
            subCourseSection.setCourseId(courseId);
            subCourseSection.setName(subTitle);
            subCourseSection.setVideoUrl(url);
            subCourseSection.setTime(time);

            // 大章添加小节
            courseSectionVO.getSections().add(subCourseSection);
            courseSections.add(courseSectionVO);
        }

        // 批量插入
        if (courseSections.size() > 0) {
            this.batchAdd(courseSections);
        }
    }

}
