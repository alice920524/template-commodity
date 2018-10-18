package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.model.CommodityPromotion;
import com.duia.commodity.model.PayDiscountDetail;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.CommodityService;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.commodity.service.UsersService;
import com.duia.security.param.CommodityParam;
import com.duia.security.param.PayOrderInfoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 后台添加学员服务类
 * <p>
 * Created by 李国勇 on 2018/6/11.
 */
@Service("payOrderNBackASTProxyServiceImpl")
public class PayOrderNBackASTProxyServiceImpl extends PayOrderNBackProxyServiceImpl implements PayOrderProxyService {

    @Autowired
    private UsersService usersService;

    /**
     * 需要覆写，添加学员可以添加专题课
     *
     * @param order
     * @param o
     * @return
     */
    @Override
    CommodityPromotion promotionOrder(PayOrder order, Object o) {
        return null;
    }

    /**
     * 查询单品商品
     *
     * @param c
     * @return
     */
    List<CommodityDTO> singleCommodity(CommodityParam c) {
        c.setBookTypeChecked(ClassTypeEnum.BOOK_TYPE_COMMON.getKey());
        return commodityService.selectAllCommodityByParam(Arrays.asList(c));
    }

    /**
     * 学员是否选择学习包：不允许
     *
     * @return
     */
    boolean isStudentSelectStudyPack(Object o) {
        return false;
    }

    @Override
    protected void orderModelExtra(PayOrder order, PayOrderInfoParam pi, Object op) {
        order.setPayTime(order.getOrderTime());
        order.setPayType(Constant.PAY_TYPE_ADDSTUDENT);
        // 填写用户信息
        if (order.getUserId() != null) {
            Users users = usersService.findUserById(order.getUserId());
            order.setVoucher(users.getUsername());
        }
    }

    @Override
    void _saveOrder(PayOrder order) {

    }

    @Override
    void setLiveTeacher(PayOrder order, PayOrderInfoParam pi, Object o) {
        super.setTeacherInfo(order, super.getDefaultLiveTeacher());
    }

    @Override
    void setCostPrice(PayOrder order, OrderAbComp o, double coursePrice, double discountPrice) {
        order.setCostPrice(0.0);
    }

    @Override
    void verifyOrderCostPrice(PayOrder payOrder, PayOrderInfoParam pi, PayDiscountDetail discountDetail, Double coursePrice) {
        // 添加学员不需要执行验证
    }
}
