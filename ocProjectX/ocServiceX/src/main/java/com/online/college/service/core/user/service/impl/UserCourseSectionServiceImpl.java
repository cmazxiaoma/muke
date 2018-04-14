package com.online.college.service.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.service.core.user.dao.UserCourseSectionDao;
import com.online.college.service.core.user.domain.UserCourseSection;
import com.online.college.service.core.user.domain.UserCourseSectionDto;
import com.online.college.service.core.user.service.IUserCourseSectionService;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-09 09:54
 * @version V1.0
 */
@Service
public class UserCourseSectionServiceImpl implements IUserCourseSectionService {

    @Autowired
    private UserCourseSectionDao userCourseSectionDao;

    @Override
    public UserCourseSection getById(Long id) {
        return userCourseSectionDao.getById(id);
    }

    @Override
    public List<UserCourseSection> queryAll(UserCourseSection queryEntity) {
        return userCourseSectionDao.queryAll(queryEntity);
    }

    @Override
    public UserCourseSection queryLatest(UserCourseSection queryEntity) {
        return userCourseSectionDao.queryLatest(queryEntity);
    }

    @Override
    public TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity,
            TailPage<UserCourseSectionDto> page) {
        Integer itemsTotalCount = userCourseSectionDao.getTotalItemsCount(queryEntity);
        List<UserCourseSectionDto> items = userCourseSectionDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);

        return page;
    }

    @Override
    public void createSelectivity(UserCourseSection entity) {
        userCourseSectionDao.createSelectivity(entity);
    }

    @Override
    public void update(UserCourseSection entity) {
        userCourseSectionDao.update(entity);
    }

    @Override
    public void updateSelectivity(UserCourseSection entity) {
        userCourseSectionDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserCourseSection entity) {
        userCourseSectionDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserCourseSection entity) {
        userCourseSectionDao.deleteLogic(entity);
    }

}
