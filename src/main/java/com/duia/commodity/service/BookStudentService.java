package com.duia.commodity.service;
import com.duia.commodity.model.BookStudent;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/07/10.
 */
public interface BookStudentService extends Service<BookStudent> {

    /**
     * 是否购买图书商品
     * */
    Integer isBuyBookCommodity(Long userId, Long comId);
}
