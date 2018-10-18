package com.duia.commodity.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.duia.commodity.service.SmsSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by 李恒名 on 2017/7/26.
 */
@Service
public class SmsSendServiceImpl implements SmsSendService,InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CCPRestSmsSDK sdk;

    @Value("${ccpsms.debug}")
    private boolean debug;
    @Value("${ccpsms.sid}")
    private String sid;
    @Value("${ccpsms.token}")
    private String token;
    @Value("${ccpsms.appid}")
    private String appid;

    @Async
    @Override
    public void send(String mobile, String templateId,String[] data) {
        HashMap<String, Object> result = sdk.sendTemplateSMS(mobile, templateId, data);
        String statusCode = (String) result.get("statusCode");
        if (!"000000".equals(statusCode)) {
            if ("160040".equals(statusCode) || "160041".equals(statusCode)) {
                String message = "该手机号：" + mobile + "的模板ID：" + templateId + "短信已超出当天发送次数限制";
                logger.warn(message);
            }
            else if("160042".equals(statusCode)) {
                String message = "短信验证码发送失败，错误码=" + statusCode + " 错误信息= " + result.get("statusMsg");
                logger.warn(message);
            }else {
                String message = "短信验证码发送失败，错误码=" + statusCode + " 错误信息= " + result.get("statusMsg");
                logger.error(message);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        sdk = new CCPRestSmsSDK();
        if (debug) {
            sdk.init("sandboxapp.cloopen.com", "8883");
        } else {
            sdk.init("app.cloopen.com", "8883");
        }
        sdk.setAccount(sid, token);
        sdk.setAppId(appid);
    }
}
