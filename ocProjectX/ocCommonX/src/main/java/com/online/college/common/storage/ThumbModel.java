package com.online.college.common.storage;

/**
 *
* @Description: 文件的缩略图及其模式字符串定义
* @author cmazxiaoma
* @date 2018-02-07 16:04
* @version V1.0
 */
public enum ThumbModel {

    THUMB_16("imageView2/2/w/16/h/16"),
    THUMB_32("imageView2/2/w/32/h/32"),
    THUMB_48("imageView2/2/w/48/h/48"),
    THUMB_64("imageView2/2/w/64/h/64"),
    THUMB_128("imageView2/2/w/128/h/128"),
    THUMB_256("imageView2/2/w/256/h/256"),
    THUMB_512("imageView2/2/w/512/h/512");


    private String value;

    private ThumbModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
