package com.online.college.wechat.wxapi.process;

import java.util.SortedMap;
import java.util.TreeMap;

import com.online.college.common.util.SecurityUtil;

/**
 *
 * @Description: TODO
 * @author majinlan
 * @date 2018-02-12 15:36
 * @version V1.0
 */
public class WxSign {

    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;

    public WxSign() {

    }

    public WxSign(String appId, String jsTicket, String url) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = SecurityUtil.getRandomString(8);
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("jsapi_ticket", jsTicket);
        map.put("noncestr", nonceStr);
        map.put("timestamp", timestamp);
        map.put("url", url);
        this.appId = appId;
        this.nonceStr = nonceStr;
        this.timestamp = timestamp;
        this.signature = SignUtil.signature(map);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
