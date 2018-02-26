package com.online.college.common.orm;

import java.io.Serializable;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-26 13:40
* @version V1.0
 */
public interface Identifier<KEY extends Serializable> {

    KEY getId();
}
