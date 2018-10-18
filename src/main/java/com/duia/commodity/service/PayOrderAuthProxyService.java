package com.duia.commodity.service;

import com.duia.commodity.model.PayOrder;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李国勇 on 2018/6/11.
 */
public interface PayOrderAuthProxyService {

    /**
     * 订单处理
     *
     * @param request
     * @param p
     * @return
     */
    PayOrder process(HttpServletRequest request, PayOrderParam p);
}
