package com.duia.commodity.service;

import com.duia.commodity.Tester;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by 李恒名 on 2017/7/18.
 */
public class CommodityServiceTestCase extends Tester {
    @Resource
    private CommodityService commodityService;

    @Test
    public void test1(){
        //假設3777 为恶意添加的商品
//        boolean pass = commodityService.validateOrderConfirmParam(StringUtils.split("3772,3602,3777",','), 1533l);
//        Assert.assertFalse(pass);
//        pass = commodityService.validateOrderConfirmParam(StringUtils.split("3772,3602",','), 1533l);
//        Assert.assertTrue(pass);
    }

    @Test
    public void test2(){
        //999 WEB / 998WAP
//        List<CommodityDTO> commodityList = commodityService.findAddMoneyCommodityByComIdAndTypeAndAppType(2592l, 0, 999);
//        Assert.assertTrue(commodityList.size() > 0);
    }
}
