package com.duia.commodity.service;

import com.duia.commodity.common.dto.BookCommidityDTO;
import com.duia.commodity.common.dto.BookOrderParamDTO;
import com.duia.commodity.dto.BookOrderPayDTO;
import com.duia.commodity.dto.BookOrderPayDTO;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.Users;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
public interface BookOrderService extends Service<BookOrder> {

    /**
     * 图书订单创建
     *
     *
     * @param request
     * @param user
     *@param paramDTO  @return
     */
    BookOrder create(HttpServletRequest request, Users user, BookOrderParamDTO paramDTO);

    /**
     * 确认订单
     */
    BookCommidityDTO confirmOrder(Long comId);

    /**
     * 订单支付
     */
    BookOrderPayDTO payOrder(String payNum, Users user);


}
