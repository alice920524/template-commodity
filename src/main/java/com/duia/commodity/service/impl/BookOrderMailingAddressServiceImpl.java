package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.BookOrderMailingAddressMapper;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.BookOrderMailingAddress;
import com.duia.commodity.model.UserMailingAddress;
import com.duia.commodity.service.BookOrderMailingAddressService;
import com.duia.commodity.service.UserMailingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
@Service
@Transactional
public class BookOrderMailingAddressServiceImpl extends AbstractService<BookOrderMailingAddress> implements BookOrderMailingAddressService {
    @Resource
    private BookOrderMailingAddressMapper bookOrderMailingAddressMapper;
    @Resource
    private UserMailingAddressService userMailingAddressService;

    /**
     * 用户邮寄地址信息
     * @param order
     */
    @Override
    public void saveBookOrderMailingAddress(BookOrder order) {
        // 查询用户邮寄地址
        UserMailingAddress userMailingAddress = userMailingAddressService.findByUserId(order.getUserId());
        BookOrderMailingAddress orderMailingAddress = new BookOrderMailingAddress();
        if (userMailingAddress != null) {
            orderMailingAddress.setId(order.getId());
            orderMailingAddress.setAddressee(userMailingAddress.getAddressee());
            orderMailingAddress.setMobile(userMailingAddress.getMobile());
            orderMailingAddress.setProvince(userMailingAddress.getProvince());
            orderMailingAddress.setCity(userMailingAddress.getCity());
            orderMailingAddress.setCounty(userMailingAddress.getCounty());
            orderMailingAddress.setDetailAddress(userMailingAddress.getDetailAddress());
            bookOrderMailingAddressMapper.insertSelective(orderMailingAddress);//保存学习包寄送信息
        } else {
            orderMailingAddress.setId(order.getId());
            bookOrderMailingAddressMapper.insertSelective(orderMailingAddress);
        }
    }
}
