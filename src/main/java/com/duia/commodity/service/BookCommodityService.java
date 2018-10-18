package com.duia.commodity.service;
import com.duia.commodity.model.BookCommodity;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
public interface BookCommodityService extends Service<BookCommodity> {

    /**
     * 图书商品状态值
     * */
    Integer selectBookCommodityStatus(Long comId, Integer appType);

}
