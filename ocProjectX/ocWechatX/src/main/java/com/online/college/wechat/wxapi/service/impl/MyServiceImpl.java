package com.online.college.wechat.wxapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.service.core.course.domain.Course;
import com.online.college.service.core.course.domain.CourseQueryDto;
import com.online.college.service.core.course.service.ICourseService;
import com.online.college.wechat.wxapi.process.MsgType;
import com.online.college.wechat.wxapi.process.MsgXmlUtil;
import com.online.college.wechat.wxapi.service.MyService;
import com.online.college.wechat.wxapi.vo.Article;
import com.online.college.wechat.wxapi.vo.MsgNews;
import com.online.college.wechat.wxapi.vo.MsgRequest;
import com.online.college.wechat.wxapi.vo.MsgResponseNews;

/**
 * 业务消息处理 开发者根据自己的业务自行处理消息的接收与回复；
 */
@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private ICourseService courseService;

    /**
     * 处理消息 开发者可以根据用户发送的消息和自己的业务，自行返回合适的消息；
     *
     * @param msgRequest
     *            : 接收到的消息
     * @param appId
     *            ： appId
     * @param appSecret
     *            : appSecret
     */
    @Override
    public String processMsg(MsgRequest msgRequest, String contextUri) {
        List<MsgNews> msgNews = new ArrayList<MsgNews>();

        // 测试图文消息，不需要数据库jdbc连接
        /*
         * MsgNews news = new MsgNews(); news.setTitle("测试在线网校图文消息");
         * news.setPicpath("http://img01.taopic.com/141128/240418-14112P9345826.jpg");
         * news.setBrief("仅供测试使用，点击进入百度"); news.setUrl("http://www.baidu.com");
         * msgNews.add(news);
         */
        // 数据库中的课程
        this.prepareCourseNews(msgNews, contextUri);

        return MsgXmlUtil.newsToXml(getMsgResponseNews(msgRequest, msgNews));
    }

    private void prepareCourseNews(List<MsgNews> msgNews, String contextUri) {
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.setStart(0);
        queryEntity.setCount(3);
        List<Course> list = courseService.queryList(queryEntity);
        for (Course c : list) {
            MsgNews news = new MsgNews();
            news.setTitle(c.getName());
            news.setBrief(c.getBrief());
            news.setMsgtype(MsgType.News.name());
            if (StringUtils.isNotEmpty(c.getPicture())) {
                news.setPicpath(QiniuStorage.getUrl(c.getPicture()));
            } else {
                news.setPicpath("http://img01.taopic.com/141128/240418-14112P9345826.jpg");
            }
            news.setUrl(contextUri + "/course/video.html?id=" + c.getId());
            msgNews.add(news);
        }
    }

    // 构造一条图文消息
    private MsgResponseNews getMsgResponseNews(MsgRequest msgRequest, List<MsgNews> msgNews) {
        if (msgNews != null && msgNews.size() > 0) {
            MsgResponseNews responseNews = new MsgResponseNews();
            responseNews.setToUserName(msgRequest.getFromUserName());
            responseNews.setFromUserName(msgRequest.getToUserName());
            responseNews.setMsgType(MsgType.News.toString());
            responseNews.setCreateTime(System.currentTimeMillis());
            responseNews.setArticleCount(msgNews.size());
            List<Article> articles = new ArrayList<Article>(msgNews.size());
            for (MsgNews n : msgNews) {
                Article a = new Article();
                a.setTitle(n.getTitle());
                a.setPicUrl(n.getPicpath());
                if (StringUtils.isEmpty(n.getFromurl())) {
                    a.setUrl(n.getUrl());
                } else {
                    a.setUrl(n.getFromurl());
                }
                a.setDescription(n.getBrief());
                articles.add(a);
            }
            responseNews.setArticles(articles);
            return responseNews;
        } else {
            return null;
        }
    }
}
