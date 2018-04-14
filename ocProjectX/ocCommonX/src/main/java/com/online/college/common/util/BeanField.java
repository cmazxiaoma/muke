package com.online.college.common.util;

import java.lang.reflect.Field;

/**
 *
 * @Description: BeanField对应着数据表列名，model类字段属性
 * @author majinlan
 * @date 2018年2月5日
 * @version V1.0
 */
public class BeanField {

    /**
     * 数据库表列名
     */
    private String columnName;

    /**
     * model类字段属性
     */
    private Field field;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
