package com.duia.commodity.service;

import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.ClassStudentBook;
import com.duia.commodity.model.PayOrderDetail;


/**
 * Created by CodeGenerator on 2018/04/09.
 */
public interface ClassStudentBookService extends Service<ClassStudentBook> {

    void savePayOrderBook(PayOrderDetail detail, Long userId, CommodityDTO commodityDTO, OrderAbComp orderAbComp);

}
