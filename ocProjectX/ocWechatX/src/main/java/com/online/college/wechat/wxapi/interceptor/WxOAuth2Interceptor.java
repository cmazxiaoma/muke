package com.online.college.wechat.wxapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.online.college.common.util.HttpUtil;
import com.online.college.wechat.wxapi.process.MpAccount;
import com.online.college.wechat.wxapi.process.OAuthScope;
import com.online.college.wechat.wxapi.process.WxApi;
import com.online.college.wechat.wxapi.process.WxApiClient;
import com.online.college.wechat.wxapi.process.WxMemoryCacheClient;

/**
 * 微信客户端用户请求验证拦截器
 */
public class WxOAuth2Interceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 开发者自行处理拦截逻辑，
	 * 方便起见，此处只处理includes
	 */
	private String[] excludes;//不需要拦截的
	private String[] includes;//需要拦截的
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		boolean oauthFlag = false;//为方便展示的参数，开发者自行处理
		for(String s : includes){
			if(uri.contains(s)){//如果包含，就拦截
				oauthFlag = true;
				break;
			}
		}
		if(!oauthFlag){//如果不需要oauth认证
			return true;
		}
		
		String sessionid = request.getSession().getId();
		
		//先从缓存中获取openid
		String openid = WxMemoryCacheClient.getOpenid(sessionid);
		if(StringUtils.isEmpty(openid)){//没有，通过微信页面授权获取
			String code = request.getParameter("code");
			/**
			 * 如果request中包括code，则是微信回调
			 */
			if(StringUtils.isNotBlank(code)){
				try {
					openid = WxApiClient.getOAuthOpenId(WxApiClient.getMpAccount(), code);
					if(!StringUtils.isBlank(openid)){
						
						//缓存openid，在具体业务代码中直接从缓存中取即可
						WxMemoryCacheClient.setOpenid(sessionid, openid);
						return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else{//oauth获取code
				MpAccount mpAccount = WxApiClient.getMpAccount();
				String redirectUrl = HttpUtil.getRequestFullUriNoContextPath(request);//请求code的回调url
				String state = OAuth2RequestParamHelper.prepareState(request);
				
				/**
				 * 请求微信OAuth认证接口
				 * 微信服务器会回调：redirectUrl （即当前拦截的url）
				 */
				String url = WxApi.getOAuthCodeUrl(mpAccount.getAppid(), redirectUrl, OAuthScope.Base.toString(), state);
				HttpUtil.redirectHttpUrl(request, response, url);
				return false;
			}
		}else{
			System.out.println("#### WxOAuth2Interceptor Session : openid = " + openid);
			return true;
		}
		HttpUtil.redirectUrl(request, response, "/error/101.html");
		return false;
	}
	
	
	public String[] getExcludes() {
		return excludes;
	}

	public void setExcludes(String[] excludes) {
		this.excludes = excludes;
	}

	public String[] getIncludes() {
		return includes;
	}

	public void setIncludes(String[] includes) {
		this.includes = includes;
	}
	
	
}

