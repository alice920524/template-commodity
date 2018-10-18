package com.duia.commodity.service;

import com.duia.commodity.model.BookCommodity;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.BookOrderDetail;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
public interface BookOrderDetailService extends Service<BookOrderDetail> {

    /**
     * 保存图书订单详情
     *
     * @param order
     * @param commodity
     */
    void saveBookOrderDetail(BookOrder order, BookCommodity commodity);
}
