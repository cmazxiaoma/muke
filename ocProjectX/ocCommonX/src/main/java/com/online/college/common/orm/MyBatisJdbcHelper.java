package com.online.college.common.orm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.online.college.common.util.BeanUtil;

/**
 *
* @Description: 为mybatis准备动态sql
* @author cmazxiaoma
* @date 2018年2月5日
* @version V1.0
 */
public class MyBatisJdbcHelper {

    public static <T> String getByIdSql(Class<T> entityClass, String... fieldNames) {
        String sql = MyBatisJdbcHelper.querySql(entityClass, null, fieldNames);
        sql += " WHERE id = #{param2}";

        return sql;
    }

    public static <T> String querySql(Class<T> entityClass, QueryFilter filter, String... fieldNames) {
        StringBuilder sql = new StringBuilder("SELECT ");
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();

        if (fieldNames != null && fieldNames.length > 0) {
            String columnSql = "";

            for (String name : fieldNames) {
                String column = BeanUtil.fieldToColumn(name);
                columnSql += " " + column.toUpperCase() + ",";
            }
            sql.append(columnSql.substring(0, columnSql.length() - 1));
        } else {
            sql.append(" * ");
        }
        sql.append(" FROM " + tableName);

        if (filter != null) {
            if (!StringUtils.isEmpty(filter.getWhere())) {
                sql.append(" WHERE " + filter.getWhere());
            }

            if (!StringUtils.isEmpty(filter.getSort())) {
                sql.append(" ORDER BY " + filter.getSort());
            }
        }

        return sql.toString();
    }

    public static <T, E> String queryByIdsSql(Class<T> entityClass, E[] ids, String... fieldNames) {
        StringBuilder where =  new StringBuilder();

        for (E e : ids) {
            where.append(e + ",");
        }
        String sql = querySql(entityClass, null, fieldNames);

        return sql + " WHERE id IN (" + where.toString().substring(0, where.length() - 1) + " )";
    }

    public static <T> String countSql(Class<T> entityClass, QueryFilter filter) {
        String tableName = BeanUtil.getTableName(entityClass);
        StringBuilder sql = new StringBuilder("SELECT COUNT(1) FROM " + tableName);

        if (filter != null) {
            if (!StringUtils.isEmpty(filter.getWhere())) {
                sql.append(" WHERE " + filter.getWhere());
            }
        }

        return sql.toString();
    }

    public static <T> String createSql(Class<T> entityClass) {
        Map<String, String> map = BeanUtil.getAllFieldColumns(entityClass);
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sb = new StringBuilder("INSERT INTO " + tableName);
        Object[] fieldNames = map.keySet().toArray();
        Object[] colNames = map.values().toArray();

        sb.append(" ( " + join(colNames).toUpperCase() + " ) ");
        sb.append(" VALUES ");
        sb.append(" ( " + join2(fieldNames) + " ) ");

        return sb.toString();
    }

    public static <T> Map<String, String> createAllSqlMap(Class<T> entityClass) {
        Map<String, String> map = BeanUtil.getAllFieldColumns(entityClass);
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sql1 = new StringBuilder("INSERT INTO " + tableName);
        Object[] fieldNames = map.keySet().toArray();
        Object[] colNames = map.values().toArray();
        sql1.append(" ( " + join(colNames).toUpperCase() + " ) ");
        sql1.append(" VALUES ");
        String sql2 = " ( " + join3(fieldNames) + " ) ";
        Map<String, String> rstMap = new HashMap<>();
        rstMap.put("sql1", sql1.toString());
        rstMap.put("sql2", sql2);

        return rstMap;
    }


    public static <T> String updateSql(Class<T> entityClass, boolean byId, QueryFilter filter,
            String... fieldNames) {
        Map<String, String> map = BeanUtil.getAllFieldColumns(entityClass);
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

        if (fieldNames != null && fieldNames.length > 0) {
            for (String name : fieldNames) {
                String column = BeanUtil.fieldToColumn(name);
                sql.append(" " + column.toUpperCase() + " = #{param2." + name + "},");
            }
        } else {
            for (String s : map.keySet()) {
                if (!"id".equals(s) && !"createUser".equals(s)
                        && !"createTime".equals(s) && !"del".equals(s)) {
                    sql.append(" " + map.get(s).toString().toUpperCase() + " = #{param2." + s + "},");
                }
            }
        }
        String updateSql = sql.toString().substring(0, sql.length() - 1);

        if (byId) {
            updateSql += " WHERE id = #{param2.id} ";
        } else {
            if (filter != null) {
                if (!StringUtils.isEmpty(filter.getWhere())) {
                    updateSql += " WHERE " + filter.getWhere();
                }
            }
        }

        return updateSql;
    }

    public static <T> String deleteSql(Class<T> entityClass, boolean byId, QueryFilter filter) {
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName);

        //根据id更新对象
        if (byId) {
            sql.append(" WHERE id = #{param2.id}");
        } else {
            //更新所有，或者根据条件来更新对象
            if (filter != null) {
                if (!StringUtils.isEmpty(filter.getWhere())) {
                    sql.append(" WHERE " + filter.getWhere());
                }
            }
        }

        return sql.toString();
    }

    public static <T> String deleteByIdSql(Class<T> entityClass) {
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName);
        sql.append(" WHERE id = #{param2}");

        return sql.toString();
    }

    public static <T> String deleteByIdSqls(Class<T> entityClass) {
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName +  "WHERE id IN ");

        return sql.toString();
    }

    public static <T> String deleteByFilterSql(Class<T> entityClass, QueryFilter filter) {
        String tableName = BeanUtil.getTableName(entityClass).toUpperCase();
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName);

        if (filter != null) {
            if (!StringUtils.isEmpty(filter.getWhere())) {
                sql.append(" WHERE " + filter.getWhere());
            }
        }

        return sql.toString();

    }

    public static String join(Object[] arr) {
        StringBuilder sb = new StringBuilder();

        for (Object s : arr) {
            sb.append(s + ",");
        }
        String str = sb.toString();

        return str.substring(0, str.length() - 1);
    }

    public static String join2(Object[] arr) {
        StringBuilder sb = new StringBuilder();

        for (Object s : arr) {
            sb.append("#{param2." + s + "},");
        }
        String str = sb.toString();

        return str.substring(0, str.length() - 1);
    }

    public static String join3(Object[] arr) {
        StringBuilder sb = new StringBuilder();

        for (Object s : arr) {
            sb.append("#{item." + s + "},");
        }
        String string = sb.toString();

        return string.substring(0, string.length() - 1);
    }
}
