package com.duia.commodity.service;

import com.duia.commodity.common.dto.CommodityResultDTO;
import com.duia.commodity.common.dto.OrderCreateSuccessDTO;
import com.duia.commodity.common.dto.UpgradeOrderConfirmDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.Users;
import com.duia.security.param.PayOrderCommodityParam;
import com.duia.security.param.UpgradeParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface PayOrderService extends Service<PayOrder> {

    /**
     * 多订单业务处理 - 分批|百度|咖啡
     *
     * @param payNum  订单号
     * @param payType 订单类型
     * @param user    用户
     * @return
     */
    ModelAndView handleMultiPayOrder(String payNum, String payType, Users user);

    /**
     * 确认订单返回信息
     *
     * @param * @param p 加密参数
     * @param * @param user 当前用户
     * @Author: Hero
     * @Date: Created in 11:17 2018/5/17
     */
    CommodityResultDTO orderConfirm(HttpServletRequest request, PayOrderCommodityParam param, Users user, Integer appType);

    /**
     * 升级订单确认
     */
    UpgradeOrderConfirmDTO orderUpgradeConfirm(HttpServletRequest request, UpgradeParam param, Users user, Integer appType);

    /**
     * 拆分子订单
     *
     * @param parentOrder
     * @param price
     * @return
     */
    PayOrder generateChildOrder(PayOrder parentOrder, Double price);


    List<PayOrder> findPayStatusNonOrder(List<Long> orderIds);

    /**
     * 订单创建成功返回信息
     *
     * */
    OrderCreateSuccessDTO findOrderCreateSuccessInfo(String payNum);
    /**
     * 订单支付成功返回信息
     *
     * */
    OrderCreateSuccessDTO findOrderPaySuccessInfo(String orderNum);

    /**
     * ios下单(暂定)
     * */
    com.duia.resultG.Result createOrderIOS(HttpServletRequest request, Users user, String p);

    /**
     * 查询订单
     * */
    PayOrder findPayOrderByPayNum(String payNum);
}
