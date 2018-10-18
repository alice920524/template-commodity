package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.BookCommodity;
import org.apache.ibatis.annotations.Param;

public interface BookCommodityMapper extends Mapper<BookCommodity> {

    /**
     * 查询图书商品信息(包含上下架)
     * */
    BookCommodity selectBookCommodity(@Param("comId") Long comId, @Param("appType") Integer appType);
}