package com.online.college.opt.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.online.college.common.util.ImportExcelUtil;
import com.online.college.common.web.JsonView;
import com.online.college.opt.business.ICourseSectionBusiness;
import com.online.college.opt.vo.CourseSectionVO;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;

/**
 *
 * @Description: 课程章节管理
 * @author cmazxiaoma
 * @date 2018-02-11 21:30
 * @version V1.0
 */
@Controller
@RequestMapping("/courseSection")
public class CourseSectionController {

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private ICourseSectionBusiness courseSectionBusiness;

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        return JsonView.render(courseSectionService.getById(id));
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(CourseSection entity) {
        courseSectionService.updateSelectivity(entity);
        return new JsonView().toString();
    }

    /**
     * 交换排序位置
     *
     * @param entity
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/sortSection")
    @ResponseBody
    public String sortSection(CourseSection entity, Integer sortType) {
        CourseSection curCourseSection = courseSectionService.getById(entity.getId());

        if (null != curCourseSection) {
            CourseSection tmpCourseSection = null;

            // 如果是降序
            // 比当前sort大的, 正序排序的第一个
            if (Integer.valueOf(1).equals(sortType)) {
                tmpCourseSection = courseSectionService.getSortSectionMax(curCourseSection);
            } else {
                // 如果是升序
                // 比当前sort小的，倒序排序的第一个
                tmpCourseSection = courseSectionService.getSortSectionMin(curCourseSection);
            }

            if (null != tmpCourseSection) {
                Integer tmpSort = curCourseSection.getSort();
                curCourseSection.setSort(tmpCourseSection.getSort());
                courseSectionService.updateSelectivity(curCourseSection);

                tmpCourseSection.setSort(tmpSort);
                courseSectionService.updateSelectivity(tmpCourseSection);
            }
        }
        return new JsonView().toString();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(CourseSection entity) {
        courseSectionService.delete(entity);
        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(CourseSection entity) {
        courseSectionService.deleteLogic(entity);
        return new JsonView().toString();
    }

    /**
     * 批量添加章节
     *
     * @param batchSections
     * @return
     */
    @RequestMapping(value = "/batchAdd")
    @ResponseBody
    public String batchAdd(@RequestBody List<CourseSectionVO> batchSections) {
        courseSectionBusiness.batchAdd(batchSections);
        return new JsonView().toString();
    }

    /**
     * 导入excel
     *
     * @param courseId
     * @param excelFile
     * @return
     * @throws Exception
     */
    @RequestMapping("/doImport")
    @ResponseBody
    public String doImport(Long courseId,
            @RequestParam(value = "courseSectionExcel", required = true) MultipartFile excelFile) throws Exception {

        if (excelFile.isEmpty() || excelFile.getSize() < 0) {
            return new JsonView().render(1, "请提供需要导入的文件");
        }

        List<List<Object>> parentList = new ImportExcelUtil()
                .getBankListByExcel(excelFile.getInputStream(),
                excelFile.getOriginalFilename());

        List<Object> objectList = Lists.newArrayList();

        for (List<Object> item : parentList) {
            if (item.size() == 0 || CollectionUtils.isEmpty(item)) {
                objectList.add(item);
            }
        }
        parentList.removeAll(objectList);

        if (!parentList.isEmpty() && !CollectionUtils.isEmpty(parentList)) {
            courseSectionBusiness.batchImport(courseId, parentList);
        }

        return new JsonView().toString();
    }
}
