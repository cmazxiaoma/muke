package com.online.college.common.orm;

/**
 *
* @Description: 简单查询，目前只支持简单的 = 和 <>查询
*               只支持3个简单的查询
* @author cmazxiaoma
* @date 2018年2月5日
* @version V1.0
 */
public class QueryFilter {

    private Integer timeCount = 0;
    private StringBuilder where = new StringBuilder("");
    private StringBuilder sort = new StringBuilder("");

    public String getWhere() {
        return where.toString();
    }

    public String getSort() {
        return sort.toString();
    }
}
