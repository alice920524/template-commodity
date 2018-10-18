package com.duia.commodity.service;

import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.BookOrderMailingAddress;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
public interface BookOrderMailingAddressService extends Service<BookOrderMailingAddress> {

    /**
     * 保存图书订单邮寄信息
     *
     * @param order
     */
    void saveBookOrderMailingAddress(BookOrder order);
}
