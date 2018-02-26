package com.online.college.common.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.online.college.common.util.PropertiesUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 *
 * @Description: 七牛云SDK的二次封装
 * @author cmazxiaoma
 * @date 2018-02-07 15:07
 * @version V1.0
 */
public class QiniuWrapper {

    private static final Logger logger = LoggerFactory.getLogger(QiniuWrapper.class);

    private static final String CONFIG_BUCKET = "qiniu.bucket";

    private static final String CONFIG_AK = "qiniu.accesskey";

    private static final String CONFIG_SK = "qiniu.secretkey";

    private static final String CONFIG_CDN = "qiniu.cdns";

    private static final Auth auth;

    private static final UploadManager uploadManager;

    private static final String bucketName;

    private static final List<String> cdns;

    /**
     * 从外部文件中初始化七牛存储的相关的配置信息
     *
     */
    static {
        Properties properties = PropertiesUtil.getDefaultProperties();
        auth = Auth.create(properties.getProperty(CONFIG_AK), properties.getProperty(CONFIG_SK));
        // 设置空间上传域名
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
        bucketName = properties.getProperty(CONFIG_BUCKET);
        String cdn = properties.getProperty(CONFIG_CDN);
        cdns = Arrays.asList(cdn.split(","));
    }

    /**
     * 获取上传资源的token
     *
     * @return
     */
    public static String getUploadToken() {
        return auth.uploadToken(bucketName);
    }

    /**
     * 获取更新资源的token，只能用于更新参数key所代表的资源
     *
     * @param key
     *            存储空间中已经存在的资源key
     * @return
     */
    public static String getUploadToken(String key) {
        return auth.uploadToken(bucketName, key);
    }

    /**
     * 上传文件
     *
     * @param data
     *            二进制格式的文件内容
     * @param key
     *            文件在七牛中的key
     * @param update
     *            是否是更新
     * @return
     */
    public static String upload(byte[] data, String key, boolean update) {
        try {
            String token = update ? auth.uploadToken(bucketName, key) : auth.uploadToken(bucketName);
            Response response = uploadManager.put(data, getFullyKey(data, key), token);
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);

            return ret.key;
        } catch (QiniuException e) {
            logger.error("upload file to qiniu cloud storage failed", e);
        }

        return null;
    }

    public static String getFullyKey(byte[] data, String key) {
        FileType type = FileTypeHelper.getType(data);

        if (type != null) {
            return key + "." + type.name().toLowerCase();
        } else {
            return key;
        }
    }

    public static String getUrl(String key, String model) {
        return getUrl(key, model, 3600);
    }

    /**
     * 获取多个key图片
     *
     * @param keys
     *            逗号隔开的多个key
     * @param model
     * @return
     */
    public static List<String> getUrls(String keys, String model) {
        List<String> list = null;

        if (org.apache.commons.lang.StringUtils.isNotBlank(keys)) {
            list = new ArrayList<>();

            for (String key : keys.split(",")) {
                list.add(getUrl(key, model, 3600));
            }
        }

        return list;
    }

    public static String getUrl(String key) {
        if (!StringUtils.isEmpty(key)) {
            return auth.privateDownloadUrl("http://" + getCDN() + "/@" + key);
        }

        return null;
    }

    /**
     *
     * @param key
     * @param expires
     *            单位：秒
     * @return
     */
    public static String getUrl(String key, long expires) {
        if (!StringUtils.isEmpty(key)) {
            long time = System.currentTimeMillis() / 1000 + expires;

            return auth.privateDownloadUrl("http://" + getCDN() + "/@" + key, time);
        }

        return null;
    }

    public static String getUrl(String key, String model, long expires) {
        if (StringUtils.hasText(model)) {
            return auth.privateDownloadUrl("http://" + getCDN() + "/@" + key + "?" + model);
        } else {
            return auth.privateDownloadUrl("http://" + getCDN() + "/@" + key, expires);
        }
    }

    /**
     * 从多条CDN路径中随机选择一条
     *
     * @return
     */
    private static String getCDN() {
        Random random = new Random();
        int num = random.nextInt(cdns.size());

        return cdns.get(num);
    }
}
