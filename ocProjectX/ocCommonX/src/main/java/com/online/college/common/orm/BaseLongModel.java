package com.online.college.common.orm;

import java.io.Serializable;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-12 13:21
* @version V1.0
 */
public class BaseLongModel implements Identifier<Long>, Serializable {

    private static final long serialVersionUID = 7978917143723588623L;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
