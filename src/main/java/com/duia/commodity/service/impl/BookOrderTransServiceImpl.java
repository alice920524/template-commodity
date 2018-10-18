package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.dao.BookOrderTransMapper;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.BookOrderTrans;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderTrans;
import com.duia.commodity.service.BookOrderTransService;
import com.duia.commodity.core.AbstractService;
import com.duia.enums.PayStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
@Service
@Transactional
public class BookOrderTransServiceImpl extends AbstractService<BookOrderTrans> implements BookOrderTransService {
    @Resource
    private BookOrderTransMapper bookOrderTransMapper;

    /**
     * 图书订单信息
     * @param order
     * @return
     */
    @Override
    public BookOrderTrans saveTrans(BookOrder order) {
        BookOrderTrans trans = new BookOrderTrans();
        trans.setPrice(order.getCostPrice());

        String outTradeNo = genOutTradeNo();
        trans.setPayNum(outTradeNo);
        trans.setTransDate(new Date());
        trans.setStatus(PayStatus.PAY_STATUS_NON.getState());
        trans.setOrderId(order.getId());
        trans.setPayType(order.getPayType());
        bookOrderTransMapper.insert(trans);
        return trans;
    }

    /**
     * 生成商户订单号
     */
    private String genOutTradeNo() {
        for (; ; ) {
            String outTradeNo = Constant.BOOK_ORDER_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
                    + RandomStringUtils.randomNumeric(9);
            if (findBy("payNum", outTradeNo) == null) {//由于该字段没有添加唯一性索引，需要确定是否重复
                return outTradeNo;
            }
        }
    }
}
