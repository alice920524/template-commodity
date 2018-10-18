package com.duia.commodity.service;

import com.duia.commodity.common.dto.DiscountResultDTO;
import com.duia.resultG.Result;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayDiscountDetail;
import com.duia.commodity.model.Users;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface PayDiscountDetailService extends Service<PayDiscountDetail> {
    /**
     * 通过优惠券号码查询优惠券信息
     * @param discountCode
     * @return
     */
    PayDiscountDetail findCanUsedDiscountByDiscountCode(String discountCode);

    /**
     * 校验优惠码、券
     */
    Result checkDiscount(Users users, String discountCode, Long comId, Long specialId, Long upgradeId);

    /**
     * 优惠券列表
     */
    DiscountResultDTO findDiscountList(Long userId, Integer appType, Long comId, Long specialId, Integer status, Integer page, Integer size);

}
