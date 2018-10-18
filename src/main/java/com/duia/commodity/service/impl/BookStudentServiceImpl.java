package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.BookStudentMapper;
import com.duia.commodity.model.BookStudent;
import com.duia.commodity.service.BookStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/07/10.
 */
@Service
@Transactional
public class BookStudentServiceImpl extends AbstractService<BookStudent> implements BookStudentService {
    @Resource
    private BookStudentMapper bookStudentMapper;

    /**
     * 商品是否购买
     * @param userId
     * @param comId
     * @return
     */
    @Override
    public Integer isBuyBookCommodity(Long userId, Long comId) {
        if (null == userId || null == comId) {
            return 0;
        }
        Condition query = new Condition(BookStudent.class);
        query.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("comId", comId)
                .andEqualTo("deleted", 0);
        List<BookStudent> bookStudentList = this.findByCondition(query);
        /*根据数据有无判断是否购买*/
        if(bookStudentList!=null && !bookStudentList.isEmpty()){
            return 1;
        }else{
            return 0;
        }


    }
}
