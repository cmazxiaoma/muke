package com.online.college.common.web.shiro.freemarker;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-07 19:02
 * @version V1.0
 */
public class PrincipalTag extends SecureTag {

    private static final Logger logger = Logger.getLogger("PrincipalTag");

    public String getType(Map params) {
        return getParam(params, "type");
    }

    public String getProperty(Map params) {
        return getParam(params, "property");
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String result = null;

        if (getSubject() != null) {
            Object principal;

            if (getType(params) == null) {
                principal = getSubject().getPrincipal();
            } else {
                principal = getPrincipalFromClassName(params);
            }

            if (principal != null) {
                String property = getProperty(params);

                if (property == null) {
                    result = principal.toString();
                } else {
                    result = getPrincipalProperty(principal, property);
                }
            }
        }

        if (result != null) {
            try {
                env.getOut().write(result);
            } catch (IOException ex) {
                throw new TemplateException("Error writing [" + result + "] to Freemarker.", ex, env);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Object getPrincipalFromClassName(Map params) {
        String type = getType(params);

        try {
            Class cls = Class.forName(type);

            return getSubject().getPrincipals().oneByType(cls);
        } catch (ClassNotFoundException ex) {
            logger.error("Unable to find class for name [" + type + "]");
        }

        return null;
    }

    public String getPrincipalProperty(Object principal, String property) throws TemplateModelException {
        try {
            // 获取Bean对象的BeanInfo
            BeanInfo beanInfo = Introspector.getBeanInfo(principal.getClass());

            // 通过BeanInfo来获取属性的描述器
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                if (propertyDescriptor.getName().equals(property)) {
                    // 读出该属性中的值，类似getXX()方法
                    Object value = propertyDescriptor.getReadMethod().invoke(principal, (Object[]) null);

                    return String.valueOf(value);
                }
            }

            throw new TemplateModelException("Property [" + property + "]" + "not found in principal of type" + "["
                    + principal.getClass().getName() + "]");

        } catch (Exception ex) {
            throw new TemplateModelException("Error reading property [" + property + "]" + "from principal of type [ "
                    + principal.getClass().getName() + "]", ex);
        }
    }
}
