package com.online.college.wechat.wxapi.service;

import com.online.college.wechat.wxapi.vo.MsgRequest;

/**
 * 我的微信服务接口，主要用于结合自己的业务和微信接口
 */
public interface MyService {
	
	//消息处理
	public String processMsg(MsgRequest msgRequest, String contextUri);
	
}



