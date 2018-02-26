package com.online.college.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 *
 * @Description: Bean转换工具
 * @author cmazxiaoma
 * @date 2018年2月5日
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public class BeanUtil {

    private static Logger log = Logger.getLogger(BeanUtil.class); // 日志

    /**
     * 将map list转换为bean list
     *
     * @param clazz
     * @param mapList
     * @return
     */
    public static <T> List<T> mapListToBeanList(Class<T> clazz, List<Map<String, Object>> mapList) {
        List<T> rstList = new ArrayList<T>();

        for (Map<String, Object> map : mapList) {
            rstList.add(mapToBean(clazz, map));
        }
        return rstList;
    }

    /**
     * 将map转换为bean
     *
     * @param clazz
     * @param map
     * @return
     */
    public static <T> T mapToBean(Class<T> clazz, Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        // 创建对象
        T instance = null;
        try {
            instance = clazz.newInstance();
            if (instance == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("类型实例化对象失败,类型:" + clazz);
            return null;
        }
        Map<String, Object> newMap = new HashMap<String, Object>();

        for (Map.Entry<String, Object> en : map.entrySet()) {
            newMap.put(columnSetMethod(en.getKey().toLowerCase()), en.getValue());
        }
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            String mname = method.getName();
            if (mname.startsWith("set")) {
                Class[] ptypes = method.getParameterTypes();
                Object v = newMap.get(mname);
                if (v != null && ptypes.length == 1) {
                    try {
                        method.invoke(instance, v);
                    } catch (Exception e) {
                        log.error("属性值装入失败,装入方法：" + ptypes + "." + method.getName() + ".参数类型" + ptypes[0] + ";装入值的类型:"
                                + v.getClass());
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 将map中的column key 转换为 field key
     *
     * @param map
     * @return
     */
    public static Map<String, Object> columnToFieldKey(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<String, Object>();

        for (Map.Entry<String, Object> en : map.entrySet()) {
            newMap.put(columnSetMethod(en.getKey()), en.getValue());
        }
        return newMap;
    }

    /**
     * 返回表名
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> String getTableName(Class<T> clazz) {
        Assert.isTrue(isEntity(clazz));
        Table table = clazz.getAnnotation(Table.class);

        if (table != null && !table.name().equals("")) {
            return table.name();
        }

        Entity entity = clazz.getAnnotation(Entity.class);

        if (entity != null && !entity.name().equals("")) {
            return entity.name();
        }
        return clazz.getSimpleName();
    }

    /**
     * 检测是否为可持久化的实体
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> boolean isEntity(Class<T> clazz) {
        return clazz.getAnnotation(Entity.class) != null || clazz.getAnnotation(Table.class) != null;
    }

    /**
     * 获取所有的属性和值，包括父类的
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> getAllFields(Object bean) {
        return getAllFields(bean, bean.getClass());
    }

    /**
     * 获取所有的属性和值，包括父类的
     *
     * @param bean
     * @param clazz
     * @return
     */
    public static Map<String, Object> getAllFields(Object bean, Class clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {
                    // 排除肯定不持久化的部分
                    if (Modifier.isTransient(field.getModifiers())) {
                        continue;
                    }
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    if (field.getAnnotation(Transient.class) != null) {
                        continue;
                    }
                    if (field.getAnnotation(Id.class) != null) {
                        continue;
                    }

                    map.put(field.getName(), PropertyUtils.getProperty(bean, field.getName()));
                }
            }
            Class superClass = clazz.getSuperclass();// 递归获取父类的Field
            Map<String, Object> superMap = getAllFields(bean, superClass);
            if (superMap != null) {
                map.putAll(superMap);
            }
            if (map.size() == 0) {
                return null;
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获取属性的值
    public static Object getProperty(Object bean, String fieldName) throws Exception {
        Class<?> fieldType = PropertyUtils.getPropertyType(bean, fieldName.trim());
        if (fieldType != null) {
            return PropertyUtils.getProperty(bean, fieldName.trim());
        }
        return null;
    }

    /**
     * 设置property的值
     *
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    public static void setProperty(Object bean, String fieldName, Object value) throws Exception {
        Class<?> fieldType = PropertyUtils.getPropertyType(bean, fieldName);

        if (value == null || "".equals(value)) {
            PropertyUtils.setProperty(bean, fieldName, null);
        } else {
            Object tmpValue = null;
            if (fieldType == String.class) {
                tmpValue = value.toString();
            } else if (fieldType == Double.class) {
                tmpValue = new Double(value.toString());
            } else if (fieldType == Double.TYPE) {
                tmpValue = Double.valueOf(new Double(value.toString()).doubleValue());
            } else if (fieldType == Float.class) {
                tmpValue = new Float(value.toString());
            } else if (fieldType == Float.TYPE) {
                tmpValue = Float.valueOf(new Float(value.toString()).floatValue());
            } else if (fieldType == Integer.class) {
                tmpValue = new Integer(value.toString());
            } else if (fieldType == Integer.TYPE) {
                tmpValue = Integer.valueOf(new Integer(value.toString()).intValue());
            } else if (fieldType == Long.class) {
                tmpValue = Long.valueOf(value.toString());
            } else if (fieldType == Long.TYPE) {
                tmpValue = Long.valueOf(Long.valueOf(value.toString()).longValue());
            } else if (fieldType == Boolean.class) {
                tmpValue = Boolean.valueOf(value.toString());
            } else if (fieldType == Boolean.TYPE) {
                tmpValue = Boolean.valueOf(Boolean.valueOf(value.toString()).booleanValue());
            } else if (fieldType == java.util.Date.class) {
                tmpValue = DateUtil.COMPAT.getTextDate((String) value);
            } else {
                tmpValue = null;
            }

            PropertyUtils.setProperty(bean, fieldName, tmpValue);
        }
    }

    /**
     * 获取属性类型
     *
     * @param bean
     * @param property
     * @return
     * @throws Exception
     */
    public static Class<?> getPropertyType(Object bean, String property) throws Exception {
        try {
            Field field = bean.getClass().getDeclaredField(property);
            if (field != null) {
                return PropertyUtils.getPropertyType(bean, property);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            return null;
        }
        return null;
    }

    /**
     * 数据库字段转换为set方法
     *
     * @param column
     * @return
     */
    public static String columnSetMethod(String column) {
        return "set" + columnToField2(column);
    }

    /**
     * 转换属性到数据库字段
     *
     * @param clazz
     * @return
     */
    public static Map<String, String> getAllFieldColumns(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            Map<String, String> map = new LinkedHashMap<String, String>();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {// 序列化ID不需要
                        String fieldName = field.getName();
                        String colName = BeanUtil.fieldToColumn(fieldName);
                        map.put(fieldName, colName);
                    }
                }
            }
            Class<?> superClass = clazz.getSuperclass();// 递归获取父类的Field
            Map<String, String> superMap = getAllFieldColumns(superClass);
            if (superMap != null) {
                map.putAll(superMap);
            }
            if (map.size() == 0) {
                return null;
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param clazz
     * @return
     */
    public static Map<String, BeanField> getAllFields(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            Map<String, BeanField> map = new LinkedHashMap<String, BeanField>();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {// 序列化ID不需要
                        String fieldName = field.getName();
                        String colName = BeanUtil.fieldToColumn(fieldName);
                        BeanField beanField = new BeanField();
                        beanField.setColumnName(colName);
                        beanField.setField(field);
                        map.put(fieldName, beanField);
                    }
                }
            }
            Class<?> superClass = clazz.getSuperclass();// 递归获取父类的Field
            Map<String, BeanField> superMap = getAllFields(superClass);
            if (superMap != null) {
                map.putAll(superMap);
            }
            if (map.size() == 0) {
                return null;
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换有下划线的String，下划线的后一个字母大写
     *
     * @param str
     * @return
     */
    public static String columnToField(String str) {
        String[] arr = str.split("_");
        if (arr != null && arr.length > 1) {
            String rstStr = arr[0];
            for (int i = 1; i < arr.length; i++) {
                rstStr += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
            }
            return rstStr;
        } else {
            return str;
        }
    }

    /**
     * 转换有下划线的String，第一个字母大写，下划线的后一个字母大写
     *
     * @param str
     * @return
     */
    public static String columnToField2(String str) {
        String[] arr = str.split("_");
        if (arr != null && arr.length > 1) {
            String rstStr = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1);
            for (int i = 1; i < arr.length; i++) {
                rstStr += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
            }
            return rstStr;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 转换将第一个字母大写变成小写，并在前面加下划线
     *
     * @param args
     */
    public static String fieldToColumn(String str) {
        char[] chars = str.toCharArray();
        String rstStr = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 64 && chars[i] < 94) {
                rstStr += ("_" + chars[i]).toLowerCase();
            } else {
                rstStr += chars[i];
            }
        }
        return rstStr;
    }

    /**
     * 首字母变大写
     *
     * @param args
     */
    public static String upperCaseFirst(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
    }

    /**
     * 首字母变小写
     *
     * @param args
     */
    public static String lowerCaseFirst(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1, str.length());
    }

}
