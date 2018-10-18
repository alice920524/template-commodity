package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.PayOrderMailingAddressMapper;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderMailingAddress;
import com.duia.commodity.model.UserMailingAddress;
import com.duia.commodity.service.PayOrderMailingAddressService;
import com.duia.commodity.service.UserMailingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
@Service
@Transactional
public class PayOrderMailingAddressServiceImpl extends AbstractService<PayOrderMailingAddress> implements PayOrderMailingAddressService {
    @Resource
    private PayOrderMailingAddressMapper payOrderMailingAddressMapper;
    @Resource
    private UserMailingAddressService userMailingAddressService;

    @Override
    public void savePayOrderMailAddress(Boolean selectedStudyPack, PayOrder order) {
        if (selectedStudyPack) {//选择了学习包
            // 查询用户邮寄地址
            UserMailingAddress userMailingAddress = userMailingAddressService.findByUserId(order.getUserId());
            PayOrderMailingAddress orderMailingAddress = new PayOrderMailingAddress();
            if (userMailingAddress != null) {
                orderMailingAddress.setId(order.getId());
                orderMailingAddress.setAddressee(userMailingAddress.getAddressee());
                orderMailingAddress.setMobile(userMailingAddress.getMobile());
                orderMailingAddress.setProvince(userMailingAddress.getProvince());
                orderMailingAddress.setCity(userMailingAddress.getCity());
                orderMailingAddress.setCounty(userMailingAddress.getCounty());
                orderMailingAddress.setDetailAddress(userMailingAddress.getDetailAddress());
                orderMailingAddress.setQqNum(userMailingAddress.getQqNum());
                orderMailingAddress.setQqValidate(userMailingAddress.getQqValidate());
                payOrderMailingAddressMapper.insertSelective(orderMailingAddress);//保存学习包寄送信息
            } else {
                orderMailingAddress.setId(order.getId());
                payOrderMailingAddressMapper.insertSelective(orderMailingAddress);
            }
        }
    }
}
