package com.duia.commodity.service;

import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.BookOrderTrans;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
public interface BookOrderTransService extends Service<BookOrderTrans> {

    BookOrderTrans saveTrans(BookOrder bookOrder);
}
