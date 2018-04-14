package com.online.college.common.storage;

import java.text.MessageFormat;

import com.online.college.common.util.CommonUtil;

/**
 *
 * @Description: 资源在七牛云存储的key生成器中 注： 资源是七牛云存储服务中的逻辑存储单元，对于每个账号，该账号里存放的每个资源
 *               都有唯一的一对宿主空间(Bucket)与键名(Key),作为标识识别
 * @author majinlan
 * @date 2018-02-07 16:21
 * @version V1.0
 */
public class QiniuKeyGenerator {

    /**
     * 多图片可以按照： /表名/字段名/业务值(refId)/时间戳处理
     */
    public static final String KEY = "/{0}/{1}/{2}/{3}";

    public static String generateKey() {
        return MessageFormat.format(KEY, "default", "all", "0", CommonUtil.getUID());
    }
}
