package com.duia.commodity.service.impl;

import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.comp.OrderUpgradeComp;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.model.Book;
import com.duia.commodity.model.ClassStudentBook;
import com.duia.commodity.model.PayOrderDetail;
import com.duia.commodity.service.BookService;
import com.duia.commodity.service.ClassStudentBookService;
import com.duia.enums.StudyPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Created by CodeGenerator on 2018/04/09.
 */
@Service
@Transactional
public class ClassStudentBookServiceImpl extends AbstractService<ClassStudentBook> implements ClassStudentBookService {
    @Resource
    private BookService bookService;

    @Override
    public void savePayOrderBook(PayOrderDetail detail, Long userId, CommodityDTO commodityDTO, OrderAbComp orderAbComp) {
        if (Objects.equals(detail.getAddressMark(), StudyPackage.VALID.getState())) {
            List<Book> bookList;
            // 升级
            if (orderAbComp instanceof OrderUpgradeComp) {
                bookList = this.bookService.findBookByUpgradeId(commodityDTO.getId());
            } else {// 非升级
                bookList = this.bookService.findBookByClassTypeId(commodityDTO.getClassType().getId());
            }

            for (Book book : bookList) {
                this.save(this.markClassStudentBook(detail, userId, book));
            }
        }
    }


    private ClassStudentBook markClassStudentBook(PayOrderDetail detail, Long userId, Book book) {
        Long orderId = detail.getOrderId();
        Long orderDetailId = detail.getId();

        ClassStudentBook classStudentBook = new ClassStudentBook();
        // 用户ID
        classStudentBook.setUserId(userId);
        classStudentBook.setCreator(userId);
        // 订单信息
        classStudentBook.setOrderId(orderId);
        classStudentBook.setOrderDetailId(orderDetailId);
        // 图书信息

        classStudentBook.setBookId(book.getId());
        classStudentBook.setTitle(book.getBookName());
        /**专题课、系统班 取不同的书籍价格*/
        if (Objects.equals(detail.getCourseType(), 0)) {// 系统班
            classStudentBook.setCostPrice(book.getCostPrice());
        } else if (Objects.equals(detail.getCourseType(), 1)) {// 专题课
            classStudentBook.setCostPrice(book.getsCostPrice());
        }

        classStudentBook.setPerformancePrice(book.getPerformancePrice() + "");
        classStudentBook.setKingdeeId(book.getKingdeeId());
        classStudentBook.setKingdeePrice(book.getKingdeePrice() + "");
        classStudentBook.setStatus(0);
        classStudentBook.setCreateTime(new Date());

        return classStudentBook;
    }


}
