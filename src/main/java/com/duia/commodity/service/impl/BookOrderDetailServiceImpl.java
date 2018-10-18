package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.BookRDTO;
import com.duia.commodity.dao.BookOrderDetailMapper;
import com.duia.commodity.model.BookCommodity;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.BookOrderDetail;
import com.duia.commodity.service.BookOrderDetailService;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.service.BookRelationService;
import com.duia.enums.Sales;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
@Service
@Transactional
public class BookOrderDetailServiceImpl extends AbstractService<BookOrderDetail> implements BookOrderDetailService {
    @Resource
    private BookOrderDetailMapper bookOrderDetailMapper;
    @Resource
    private BookRelationService bookRelationService;

    /**
     * 组装图书订单详情
     * @param order
     * @param commodity
     */
    @Override
    public void saveBookOrderDetail(BookOrder order, BookCommodity commodity) {

        // 查询图书商品对应的图书信息
        List<BookRDTO> books = bookRelationService.findBookCommodityDetails(commodity.getId(), Sales.BOOK.getState());
        if (!CollectionUtils.isEmpty(books)) {
            List<BookOrderDetail> orderDetails = this.makeBookOrderDetails(books, order);
            this.save(orderDetails);
        }

    }

    /**
     * 组装图书订单详情
     *
     * @param books
     * @param order
     */
    private List<BookOrderDetail> makeBookOrderDetails(List<BookRDTO> books, BookOrder order) {
        BookOrderDetail orderDetail;
        List<BookOrderDetail> orderDetails = new ArrayList<>();

        BookRDTO rdto;
        for (int i = 0; i < books.size(); i++) {
            rdto = books.get(i);
            orderDetail = new BookOrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setBookId(rdto.getId());
            orderDetail.setKingdeeId(rdto.getKingdeeId());
            //0
            if(rdto.getKingdeePrice()!=null){
                orderDetail.setKingdeePrice(rdto.getKingdeePrice().toString());
            }else{
                orderDetail.setKingdeePrice("0");
            }
            orderDetail.setTitle(rdto.getBookName());
            orderDetail.setCostPrice(rdto.getAdjustPrice());
            orderDetail.setRealPrice(rdto.getCostPrice());
            if(rdto.getPerformancePrice()!=null){
                orderDetail.setPerformancePrice(rdto.getPerformancePrice().toString());
            }else{
                orderDetail.setPerformancePrice("0");
            }
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
