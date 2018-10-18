package com.duia.commodity.dao;

import com.duia.commodity.common.dto.BookCommidityDTO;
import com.duia.commodity.common.dto.BookOrderParamDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.dto.BookOrderPayDTO;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.Users;
import org.apache.ibatis.annotations.Param;

public interface BookOrderMapper extends Mapper<BookOrder> {
    /**
     * 确认订单
     */
    BookCommidityDTO confirmOrder(Long comId);

    /**
     * 订单支付
     */
    BookOrderPayDTO payOrder(@Param("payNum") String payNum,@Param("userId")  Long userId);
}