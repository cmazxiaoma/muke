package com.online.college.wechat.wxapi.process;

/**
 *
* @Description: 消息类型
* @author cmazxiaoma
* @date 2018-02-12 15:34
* @version V1.0
 */
public enum OAuthScope {

    Base("snsapi_base"),//用户openid
    Userinfo("snsapi_userinfo");//用户信息

    private String name;

    private OAuthScope(String name) {
         this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
