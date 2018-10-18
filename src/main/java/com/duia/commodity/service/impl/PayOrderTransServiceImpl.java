package com.duia.commodity.service.impl;


import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.PayOrderTransMapper;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderTrans;
import com.duia.commodity.service.PayOrderTransService;
import com.duia.enums.PayStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2017/07/12.
 */
@Service
@Transactional
public class PayOrderTransServiceImpl extends AbstractService<PayOrderTrans> implements PayOrderTransService {
    @Resource
    private PayOrderTransMapper transMapper;

    @Override
    public PayOrderTrans saveTrans(PayOrder order) {
        PayOrderTrans trans = new PayOrderTrans();
        trans.setPrice(order.getCostPrice());

        String outTradeNo = genOutTradeNo();
        trans.setPayNum(outTradeNo);
        trans.setTransDate(new Date());
        trans.setStatus(PayStatus.PAY_STATUS_NON.getState());
        trans.setOrderId(order.getId());
        trans.setPayType(order.getPayType());
        trans.setBankType(order.getBankType());
        transMapper.insert(trans);
        return trans;
    }

    //生成商户订单号
    private String genOutTradeNo() {
        for (; ; ) {
            String outTradeNo = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS" )
                    + RandomStringUtils.randomNumeric(10);
            if (findBy("payNum", outTradeNo) == null) {//由于该字段没有添加唯一性索引，需要确定是否重复
                return outTradeNo;
            }
        }
    }

}
