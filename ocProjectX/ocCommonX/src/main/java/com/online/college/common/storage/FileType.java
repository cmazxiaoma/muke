package com.online.college.common.storage;

/**
 *
 * @Description: 文件类型枚举类
 * @author majinlan
 * @date 2018-02-07 14:48
 * @version V1.0
 */
public enum FileType {
    JPEG("FFD8FF"),

    PNG("89504E47"),

    GIF("47494638");

    private String value = "";

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
