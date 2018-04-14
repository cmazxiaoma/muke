package com.online.college.common.web.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-07 19:42
 * @version V1.0
 */
public class NotAuthenticatedTag extends SecureTag {

    private static final Logger log = Logger.getLogger("NotAuthenticatedTag");

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if (getSubject() == null || !getSubject().isAuthenticated()) {
            log.debug("Subject does not exist or is not authenticated." + "Tag body will be evaluated.");
        } else {
            log.debug("Subject exists and is authenticated." + " Tag body will not be evaluated.");
        }
    }
}
