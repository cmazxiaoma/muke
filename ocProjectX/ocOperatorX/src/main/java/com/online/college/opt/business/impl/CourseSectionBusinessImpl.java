package com.online.college.opt.business.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.web.SessionContext;
import com.online.college.opt.business.ICourseSectionBusiness;
import com.online.college.opt.vo.CourseSectionVO;
import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;

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

    /**
     * 批量添加
     */
    @Override
    public void batchAdd(List<CourseSectionVO> courseSections) {
        if (CollectionUtils.isNotEmpty(courseSections)) {
            //先获取最大的排序id
            Integer maxSort = courseSectionService.getMaxSort(
                    courseSections.get(0).getCourseId());

            for (int i = 0; i < courseSections.size(); i++) {
                CourseSectionVO tmpVO = courseSections.get(i);

                if (null == maxSort) {
                    maxSort = 0;
                }

                maxSort += (i + 1);
                CourseSection courseSection = new CourseSection();
                courseSection.setCourseId(tmpVO.getCourseId());
                courseSection.setName(tmpVO.getName());
                //大章的parentId默认为0
                courseSection.setParentId(0L);
                courseSection.setSort(maxSort);
                courseSection.setOnsale(CourseEnum.ONSALE.value());
                courseSection.setCreateTime(new Date());
                courseSection.setUpdateTime(new Date());
                courseSection.setCreateUser(SessionContext.getUsername());
                courseSection.setUpdateUser(SessionContext.getUsername());

                //创建大章
                courseSectionService.createSelectivity(courseSection);

                List<CourseSection> subCourseSections = tmpVO.getSections();

                if (CollectionUtils.isNotEmpty(subCourseSections)) {
                    String totalTime = "00:00";

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

                        Pattern p = Pattern.compile("^([0-5][0-9]):([0-5][0-9])$");

                        //正则表达式匹配不成功
                        if (!p.matcher(courseSectionTmp.getTime()).find()) {
                            courseSectionTmp.setTime("00:00");
                        }

                        if (null == courseSectionTmp.getVideoUrl()) {
                            courseSectionTmp.setVideoUrl("");
                        }

                        //计算上传 一个大章下所有的小节 总时间
                        totalTime = appendCourseSectionTime(totalTime, courseSectionTmp.getTime());
                    }
                    //创建小节
                    courseSectionService.createList(subCourseSections);

                    //更新大章的时间
                    courseSection.setTime(totalTime);
                    courseSectionService.updateSelectivity(courseSection);
                }
            }
        }
    }

    private String appendCourseSectionTime(String time1, String time2) {
        String[] time1Arr = time1.split(":");
        String[] time2Arr = time2.split(":");

        Integer second1 = Integer.parseInt(time1Arr[0]) * 60
                + Integer.parseInt(time1Arr[1]);
        Integer second2 = Integer.parseInt(time2Arr[0]) * 60
                + Integer.parseInt(time2Arr[1]);

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
    public void batchImport(Long courseId, InputStream is) {
        // TODO Auto-generated method stub
        try {
            Workbook wb = WorkbookFactory.create(is);
            //得到总行数
            Sheet sheet = wb.getSheetAt(0);
            //第一行(title移除掉)
            sheet.removeRow(sheet.getRow(0));

            List<CourseSectionVO> courseSections = new ArrayList<CourseSectionVO>();
            //遍历行
            for (Row row : sheet) {
                //章标题
                Cell title = row.getCell(0, Row.CREATE_NULL_AS_BLANK);

                //节标题
                Cell subTitle = row.getCell(1, Row.CREATE_NULL_AS_BLANK);

                //节视频url
                Cell url = row.getCell(2, Row.CREATE_NULL_AS_BLANK);

                //节时长
                Cell time = row.getCell(3, Row.CREATE_NULL_AS_BLANK);

                //如果有数据, 新建一章
                if (title.getCellType() == Cell.CELL_TYPE_STRING) {

                    if ("end".equals(title.getStringCellValue())) {
                        break;
                    }
                    //大章
                    CourseSectionVO courseSectionVO = new CourseSectionVO();
                    courseSectionVO.setCourseId(courseId);
                    courseSectionVO.setName(title.getStringCellValue().trim());

                    //小节
                    CourseSectionVO subCourseSectionVO = new CourseSectionVO();
                    subCourseSectionVO.setCourseId(courseId);
                    subCourseSectionVO.setName(subTitle.getStringCellValue().trim());
                    subCourseSectionVO.setVideoUrl(url.getStringCellValue().trim());
                    subCourseSectionVO.setTime(time.getStringCellValue().trim());

                    //大章添加小节
                    courseSectionVO.getSections().add(subCourseSectionVO);
                    courseSections.add(courseSectionVO);

                } else if (title.getCellType() == Cell.CELL_TYPE_BLANK) {
                    if (courseSections.size() > 0) {
                        CourseSectionVO lastCourseSectionVO = courseSections.get(0);

                        CourseSection subCourseSection = new CourseSection();
                        subCourseSection.setCourseId(courseId);
                        subCourseSection.setName(subTitle.getStringCellValue().trim());
                        subCourseSection.setVideoUrl(url.getStringCellValue().trim());
                        subCourseSection.setTime(time.getStringCellValue().trim());

                        lastCourseSectionVO.getSections().add(subCourseSection);
                    }
                }
            }

            //批量插入
            if (courseSections.size() > 0) {
                this.batchAdd(courseSections);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
