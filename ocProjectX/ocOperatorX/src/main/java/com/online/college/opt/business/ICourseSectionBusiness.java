package com.online.college.opt.business;

import java.io.InputStream;
import java.util.List;

import com.online.college.opt.vo.CourseSectionVO;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-11 19:13
 * @version V1.0
 */
public interface ICourseSectionBusiness {

    /**
     * 批量添加
     *
     * @param courseSections
     */
    void batchAdd(List<CourseSectionVO> courseSections);

    /**
     * 批量导入
     *
     * @param courseId
     * @param is
     */
    void batchImport(Long courseId, InputStream is);
}
