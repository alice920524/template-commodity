package com.duia.commodity.service;

import com.duia.commodity.common.dto.BookRDTO;
import com.duia.commodity.model.BookRelation;
import com.duia.commodity.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/09.
 */
public interface BookRelationService extends Service<BookRelation> {

    /**
     * 查询图书商品配置的图书
     *
     * @param relationId
     * @param relationType
     * @return
     */
    List<BookRDTO> findBookCommodityDetails(Long relationId, Integer relationType);

}
