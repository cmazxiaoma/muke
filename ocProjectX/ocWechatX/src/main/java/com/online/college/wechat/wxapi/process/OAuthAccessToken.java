package com.online.college.wechat.wxapi.process;

/**
 *
 * @Description: OAuth token
 * @author cmazxiaoma
 * @date 2018-02-12 15:33
 * @version V1.0
 */
public class OAuthAccessToken extends AccessToken {

    // oauth2.0
    private String oauthAccessToken;// 刷新token
    private String openid;
    private String scope;

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
