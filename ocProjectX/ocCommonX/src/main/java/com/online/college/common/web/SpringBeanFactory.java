package com.online.college.common.web;

import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 *
 * @Description: SpringBeanFactory
 * @author cmazxiaoma
 * @date 2018年2月5日
 * @version V1.0
 */
public class SpringBeanFactory {

    @SuppressWarnings("resource")
    public static Object getBean(String[] paths, String name) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocations(paths);
        context.setServletContext(new MockServletContext(""));
        context.refresh();
        return context.getBean(name);
    }

    public static Object getBean(String name) {
        String[] paths = { "applicationContext.xml" };
        return getBean(paths, name);
    }
}
