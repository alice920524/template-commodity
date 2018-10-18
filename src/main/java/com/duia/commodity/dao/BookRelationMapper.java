package com.duia.commodity.dao;

import com.duia.commodity.common.dto.BookRDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.BookRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookRelationMapper extends Mapper<BookRelation> {

    /**
     * 查询图书商品订单详情
     *
     * @param relationId
     * @param relationType
     * @return
     */
    List<BookRDTO> selectBookCommodityDetails(@Param("relationId") Long relationId, @Param("relationType") Integer relationType);
}