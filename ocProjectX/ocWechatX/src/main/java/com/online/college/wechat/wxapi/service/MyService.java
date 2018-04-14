package com.online.college.wechat.wxapi.service;

import com.online.college.wechat.wxapi.vo.MsgRequest;

/**
 *
 * @Description: 我的微信服务接口，主要用于结合自己的业务和微信接口
 * @author majinlan
 * @date 2018-02-24 10:46
 * @version V1.0
 */
public interface MyService {

    // 消息处理
    public String processMsg(MsgRequest msgRequest, String contextUri);

}
