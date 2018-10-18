package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.BookRDTO;
import com.duia.commodity.dao.BookRelationMapper;
import com.duia.commodity.model.BookRelation;
import com.duia.commodity.service.BookRelationService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/09.
 */
@Service
@Transactional
public class BookRelationServiceImpl extends AbstractService<BookRelation> implements BookRelationService {
    @Resource
    private BookRelationMapper bookRelationMapper;

    /**
     * 查询图书详情
     * @param relationId
     * @param relationType
     * @return
     */
    @Override
    public List<BookRDTO> findBookCommodityDetails(Long relationId, Integer relationType) {
        return bookRelationMapper.selectBookCommodityDetails(relationId, relationType);
    }
}
