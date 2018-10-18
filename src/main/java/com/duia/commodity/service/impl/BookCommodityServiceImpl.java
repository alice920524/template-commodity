package com.duia.commodity.service.impl;

import com.duia.commodity.dao.BookCommodityMapper;
import com.duia.commodity.model.BookCommodity;
import com.duia.commodity.service.BookCommodityService;
import com.duia.commodity.core.AbstractService;
import com.duia.enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
@Service
@Transactional
public class BookCommodityServiceImpl extends AbstractService<BookCommodity> implements BookCommodityService {
    @Resource
    private BookCommodityMapper bookCommodityMapper;

    /**
     *图书商品状态值 1:上架 0:下架
     * @param comId
     * @param appType
     * @return
     */
    @Override
    public Integer selectBookCommodityStatus(Long comId, Integer appType) {
        if (null == comId || null == appType) {
            //表示已经下架
            return Status.INVALID.getState();
        }

        BookCommodity bookCommodity = this.bookCommodityMapper.selectBookCommodity(comId, appType);
        Integer state = Status.INVALID.getState();
        if(null == bookCommodity || state.equals(bookCommodity.getStatus())){
            //表示已经下架
            return state;
        }

        return Status.VALID.getState();
    }


}
