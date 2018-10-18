package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.PayOrderAuthProxyService;
import com.duia.commodity.service.SmsSendService;
import com.duia.commodity.service.UsersService;
import com.duia.enums.PayType;
import com.duia.security.param.PayOrderCommodityParam;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Transactional
@Service("frontOrderAuthService")
public class PayOrderAuthFrontProxyServiceImpl extends PayOrderAuthProxyServiceImpl implements PayOrderAuthProxyService {
    private static Logger logger = Logger.getLogger(PayOrderAuthFrontProxyServiceImpl.class);
    @Resource
    private SmsSendService smsSendService;
    @Resource
    private UsersService usersService;
    @Value("${ccpsms.template.id.create-order}")
    private String templateId;

    @Override
    void constructParameters(HttpServletRequest request, PayOrderParam p, PayOrder payOrder) {
        // 设置操作平台、终端
        String appType = request.getParameter("appType");
        String os = request.getParameter("os");
        // appType (null、"null"、999、998) 属于web
        if (StringUtils.isBlank(appType)
                || StringUtils.isBlank(os)
                || "null".equals(appType)
                || "null".equals(os)
                || Constant.WAP_TYPE.toString().equals(appType)
                || Constant.WEB_TYPE.toString().equals(appType)) {
            payOrder.setSource(Constant.ORDER_SOURCE_WEB);
            payOrder.setOs(0);
        } else {
            payOrder.setSource(com.duia.commodity.common.Constant.ORDER_SOURCE_IOS + appType);
            payOrder.setOs(Integer.valueOf(os));
        }

        // 设置用户openId
        payOrder.setOpenId(this.getOpenId(request, p.getC()));
    }

    @Override
    void validateAndMakeChildOrders(PayOrder order, PayOrderInfoParam infoParam) {
        // 不需要验证订单支付信息
    }

    @Override
    boolean validateAndSendVipPermissions(PayOrder order) {
        logger.info("validateAndSendVipPermissions>>>PayOrder>>>" + JSON.toJSONString(order));
        // 前台下单成功。发短信
        Users users = this.usersService.findUserById(order.getUserId());
        if (null != users) {
            smsSendService.send(users.getMobile(), templateId, new String[]{""});
        }
        // 不要钱的订单，下单后就需要开班
        if (PayType.PAY_TYPE_ZERO.getState().equals(order.getPayType())
                || PayType.PAY_STATUS_FREE.getState().equals(order.getPayType())) {
            return true;
        }
        // 不予开班授权
        return false;
    }

    /**
     * 获取用户openId
     *
     * @param request
     * @param c
     * @return
     */
    private String getOpenId(HttpServletRequest request, PayOrderCommodityParam c) {
        if (c != null && StringUtils.isNotBlank(c.getWxOpenId())) {
            return c.getWxOpenId();
        }
        return (String) request.getSession().getAttribute("openid");
    }

}
