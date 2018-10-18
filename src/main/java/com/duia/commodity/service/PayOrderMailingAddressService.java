package com.duia.commodity.service;

import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderMailingAddress;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
public interface PayOrderMailingAddressService extends Service<PayOrderMailingAddress> {

    /**
     * 保存订单邮寄地址
     *
     * @param selectedStudyPack
     * @param order
     */
    void savePayOrderMailAddress(Boolean selectedStudyPack, PayOrder order);

}
