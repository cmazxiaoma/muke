package com.online.college.wechat.wxapi.process;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
* @Description: 微信 API、微信基本接口
* @author cmazxiaoma
* @date 2018-02-12 15:34
* @version V1.0
 */
public class WxApi {

	//token 接口
	private static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	//获取账号粉丝信息
	private static final String GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

	//网页授权OAuth2.0获取code
	private static final String GET_OAUTH_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=%s#wechat_redirect";

	//网页授权OAuth2.0获取token
	private static final String GET_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	//网页授权OAuth2.0获取用户信息
	private static final String GET_OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

	//js ticket
	private static final String GET_JSAPI_TICKET="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";


	//获取token接口
	public static String getTokenUrl(String appId,String appSecret){
		return String.format(TOKEN, appId, appSecret);
	}


	//获取粉丝信息接口
	public static String getFansInfoUrl(String token,String openid){
		return String.format(GET_FANS_INFO, token, openid);
	}


	//网页授权OAuth2.0获取code
	public static String getOAuthCodeUrl(String appId ,String redirectUrl ,String scope ,String state){
		return String.format(GET_OAUTH_CODE, appId, urlEnodeUTF8(redirectUrl), "code", scope, state);
	}

	//网页授权OAuth2.0获取token
	public static String getOAuthTokenUrl(String appId ,String appSecret ,String code ){
		return String.format(GET_OAUTH_TOKEN, appId, appSecret, code);
	}

	//网页授权OAuth2.0获取用户信息
	public static String getOAuthUserinfoUrl(String token ,String openid){
		return String.format(GET_OAUTH_USERINFO, token, openid);
	}

	//获取js ticket url
	public static String getJsApiTicketUrl(String token){
		return String.format(GET_JSAPI_TICKET, token);
	}


	/**
	 * 获取创建临时二维码post data
	 * @param expireSecodes 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param scene 临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000)
	 * @return
	 */
	public static String getQrcodeJson(Integer expireSecodes, Integer scene){
		String postStr = "{\"expire_seconds\":%d,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}";
		return String.format(postStr, expireSecodes, scene);
	}
	/**
	 * 获取创建临时二维码post data
	 * @param scene 临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000)
	 * @return
	 */
	public static String getQrcodeLimitJson(Integer scene){
		String postStr = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}";
		return String.format(postStr, scene);
	}
	//获取永久二维码
	public static String getQrcodeLimitJson(String sceneStr){
		String postStr = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":%s}}";
		return String.format(postStr, sceneStr);
	}

	//获取接口访问凭证
	public static AccessToken getAccessToken(String appId, String appSecret) {
		AccessToken token = null;
		String tockenUrl = WxApi.getTokenUrl(appId, appSecret);
		JSONObject jsonObject = httpsRequest(tockenUrl, HttpMethod.GET, null);
		if (null != jsonObject && !jsonObject.containsKey("errcode")) {
			try {
				token = new AccessToken();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;//获取token失败
			}
		}else if(null != jsonObject){
			token = new AccessToken();
			token.setErrcode(jsonObject.getInt("errcode"));
		}
		return token;
	}

	//获取js ticket
	public static JSTicket getJSTicket(String token){
		JSTicket jsTicket = null;
		String jsTicketUrl = WxApi.getJsApiTicketUrl(token);
		JSONObject jsonObject = httpsRequest(jsTicketUrl, HttpMethod.GET, null);
		if (null != jsonObject && jsonObject.containsKey("errcode") && jsonObject.getInt("errcode") == 0) {
			try {
				jsTicket = new JSTicket();
				jsTicket.setTicket(jsonObject.getString("ticket"));
				jsTicket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				jsTicket = null;//获取token失败
			}
		}else if(null != jsonObject){
			jsTicket = new JSTicket();
			jsTicket.setErrcode(jsonObject.getInt("errcode"));
		}
		return jsTicket;
	}

	//获取OAuth2.0 Token
	public static OAuthAccessToken getOAuthAccessToken(String appId, String appSecret,String code) {
		OAuthAccessToken token = null;
		String tockenUrl = getOAuthTokenUrl(appId, appSecret, code);
		JSONObject jsonObject = httpsRequest(tockenUrl, HttpMethod.GET, null);
		if (null != jsonObject && !jsonObject.containsKey("errcode")) {
			try {
				token = new OAuthAccessToken();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				token.setOpenid(jsonObject.getString("openid"));
				token.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				token = null;//获取token失败
			}
		}else if(null != jsonObject){
			token = new OAuthAccessToken();
			token.setErrcode(jsonObject.getInt("errcode"));
		}
		return token;
	}

	//发送请求
	public static JSONObject httpsRequest(String requestUrl, String requestMethod) {
		return httpsRequest(requestUrl,requestMethod,null);
	}

	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			TrustManager[] tm = { new JEEWeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static byte[] httpsRequestByte(String requestUrl, String requestMethod) {
		return httpsRequestByte(requestUrl,requestMethod,null);
	}

	public static byte[] httpsRequestByte(String requestUrl, String requestMethod, String outputStr) {
		try {
			TrustManager[] tm = { new JEEWeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
		    byte[] buffer = new byte[4096];
		    int n = 0;
		    while (-1 != (n = inputStream.read(buffer))) {
		        output.write(buffer, 0, n);
		    }
		    return output.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

class JEEWeiXinX509TrustManager implements X509TrustManager {
	@Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
	@Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
	@Override
    public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}