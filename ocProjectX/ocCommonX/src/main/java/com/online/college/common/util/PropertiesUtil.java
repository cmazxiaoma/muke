package com.online.college.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-26 13:41
 * @version V1.0
 */
public class PropertiesUtil {

    private static Map<String, Properties> propMap = new HashMap<String, Properties>();

    /* 设置默认的properties文件，方便操作 */
    public static final String DEFAULT_PROPERTIES_FILE = "application.properties";

    public static Object getProperty(String file, String key) {
        Properties prop = getProperties(file);

        if (prop != null && prop.get(key) != null) {
            return prop.get(key);
        }
        return null;
    }

    public static Properties getProperties(String file) {
        try {
            if (propMap.get(file) == null) {
                Properties prop = new Properties();
                prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(file));
                propMap.put(file, prop);
                return prop;
            } else {
                return propMap.get(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateProperties(Properties prop, String filePath) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            URI fileUri = PropertiesUtil.class.getClassLoader().getResource(filePath).toURI();
            File file = new File(fileUri);

            Properties tmpProp = new Properties();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            tmpProp.load(bis);

            FileOutputStream fos = new FileOutputStream(file);

            for (Object key : prop.keySet()) {
                tmpProp.setProperty(String.valueOf(key), String.valueOf(prop.get(key)));
            }
            tmpProp.store(fos, null);
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从默认配置文件中获取properties
     *
     * @return
     */
    public static Properties getDefaultProperties() {
        return getProperties(DEFAULT_PROPERTIES_FILE);
    }

    /**
     * 从默认配置文件中获取配置项
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        Properties prop = getDefaultProperties();

        if (prop != null && prop.get(key) != null) {
            return prop.getProperty(key);
        }
        return null;
    }
}
