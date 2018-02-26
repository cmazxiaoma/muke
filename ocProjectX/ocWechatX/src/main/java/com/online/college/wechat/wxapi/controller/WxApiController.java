package com.online.college.wechat.wxapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.web.HttpHelper;
import com.online.college.wechat.wxapi.process.MpAccount;
import com.online.college.wechat.wxapi.process.MsgXmlUtil;
import com.online.college.wechat.wxapi.process.SignUtil;
import com.online.college.wechat.wxapi.process.WxApiClient;
import com.online.college.wechat.wxapi.process.WxMemoryCacheClient;
import com.online.college.wechat.wxapi.service.impl.MyServiceImpl;
import com.online.college.wechat.wxapi.vo.AccountFans;
import com.online.college.wechat.wxapi.vo.MsgRequest;

/**
 *
 * @Description: 微信与开发者服务器交互接口
 * @author cmazxiaoma
 * @date 2018-02-12 15:30
 * @version V1.0
 */
@Controller
@RequestMapping("/wxapi")
public class WxApiController {

    @Autowired
    private MyServiceImpl myService;

    /**
     * GET请求：进行URL、Token 认证； 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
     * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(HttpServletRequest request) {
        // 根据url中的account参数获取对应的MpAccount处理即可
        MpAccount mpAccount = WxApiClient.getMpAccount();// 获取公众号
        if (mpAccount != null) {
            String token = mpAccount.getToken();// 获取token，进行验证；
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");// 随机字符串

            // 校验成功返回 echostr，成功成为开发者；否则返回error，接入失败
            if (SignUtil.validSign(signature, token, timestamp, nonce)) {
                return echostr;
            }
        }
        return "error";
    }

    /**
     * POST 请求：进行消息处理；
     */
    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseBody
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        // 处理用户和微信公众账号交互消息
        try {
            MsgRequest msgRequest = MsgXmlUtil.parseXml(request);// 获取发送的消息
            String contextUri = HttpHelper.getContextHttpUri(request);
            return myService.processMsg(msgRequest, contextUri);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 测试微信OAuth认证
     */
    @RequestMapping(value = "/oauthTest")
    public ModelAndView oauthTest(HttpServletRequest request) {
        MpAccount mpAccount = WxApiClient.getMpAccount();// 获取缓存中的唯一账号
        if (mpAccount != null) {
            ModelAndView mv = new ModelAndView("test/oauthTest");

            /**
             * 在拦截器WxOAuth2Interceptor中缓存了 openid 这里直接取
             */
            String openid = WxMemoryCacheClient.getOpenid(request.getSession().getId());
            if (null != openid) {
                AccountFans fans = WxApiClient.getAccountFans(openid);
                mv.addObject("openid", openid);
                mv.addObject("curUser", fans);
            }
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("common/failure");
            mv.addObject("message", "OAuth获取openid失败");
            return mv;
        }
    }

}