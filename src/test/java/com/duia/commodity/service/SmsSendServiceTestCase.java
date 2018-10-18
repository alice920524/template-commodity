package com.duia.commodity.service;

import com.duia.commodity.Tester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by 李恒名 on 2017/7/26.
 */
public class SmsSendServiceTestCase extends Tester {
    @Autowired
    private SmsSendService smsSendService;

    @Value("${ccpsms.template.id.create-order}")
    private String templateId;

    @Test
    public void test(){
        smsSendService.send("17701393949",templateId,new String[]{""});
    }
}
