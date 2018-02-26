package com.online.college.common.web.shiro.freemarker;

/**
 *
 * @Description: TODO
 * @author cmazxiaoma
 * @date 2018-02-07 19:50
 * @version V1.0
 */
public class LacksPermissionTag extends PermissionTag {

    @Override
    protected boolean showTagBody(String p) {
        return !isPermitted(p);
    }
}
