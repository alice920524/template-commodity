package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.BookCommidityDTO;
import com.duia.commodity.common.dto.BookOrderParamDTO;
import com.duia.commodity.common.exception.CheckBookOrderParamException;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.BookOrderMapper;
import com.duia.commodity.dto.BookOrderPayDTO;
import com.duia.commodity.model.BookCommodity;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.*;
import com.duia.enums.PayStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Created by CodeGenerator on 2018/06/25.
 */
@Service
@Transactional
public class BookOrderServiceImpl extends AbstractService<BookOrder> implements BookOrderService {

    @Resource
    private BookOrderMapper bookOrderMapper;
    @Resource
    private SysDictService sysDictService;
    @Resource
    private BookOrderDetailService bookOrderDetailService;
    @Resource
    private BookOrderMailingAddressService bookOrderMailingAddressService;
    @Resource
    private BookCommodityService bookCommodityService;

    /**
     * 创建图书订单
     * @param request
     * @param user
     * @param paramDTO  @return
     * @return
     */
    @Override
    public BookOrder create(HttpServletRequest request, Users user, BookOrderParamDTO paramDTO) {
        BookCommodity commodity = bookCommodityService.findById(paramDTO.getComId());
        if( commodity == null ){
            throw  new CheckBookOrderParamException("没有查询到此商品");
        }
        if(commodity.getStatus()==0){
            throw new CheckBookOrderParamException("商品为下架状态");
        }


        // 创建订单基本信息
        BookOrder order = this.makeOrder(request, paramDTO, user.getId());

        // 商品信息赋值
        this.makeComParamOrderData(order, commodity);

        this.save(order);

        // 保存订单详情
        bookOrderDetailService.saveBookOrderDetail(order, commodity);

        // 生成订单邮寄信息
        bookOrderMailingAddressService.saveBookOrderMailingAddress(order);

        return order;
    }

    /**
     * 图书商品订单信息
     *
     * @param order
     * @param commodity
     */
    private void makeComParamOrderData(BookOrder order, BookCommodity commodity) {
        // 商品信息
        order.setProgramId(commodity.getId());
        order.setProgramName(commodity.getTitle());
        order.setCategoryId(commodity.getSku());
        order.setCategoryName(sysDictService.findById(commodity.getSku().longValue()).getDicName());
        order.setCostPrice(commodity.getPrice());
        order.setRealpayPrice(commodity.getPrice());
        order.setCoverUrl(commodity.getCoverUrl());
    }

    /**
     * 创建图书订单基本信息
     *
     * @param request
     * @param param
     * @param userId
     * @return
     */
    private BookOrder makeOrder(HttpServletRequest request, BookOrderParamDTO param, Long userId) {
        //将与创建订单相关的信息，存入order中
        BookOrder order = new BookOrder();
        order.setUserId(userId);
        order.setOrderTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        order.setCreateTime(order.getOrderTime());
        order.setPayNum(genOutTradeNo());
        order.setPayStatus(PayStatus.PAY_STATUS_NON.getState());
        if (null == param.getSource()) {
            order.setSource(Constant.ORDER_SOURCE_WEB);
        } else {
            order.setSource(param.getSource());
        }

        order.setDeleteMark(0);
        order.setRemark(param.getRemark());
        String openid = (String) request.getSession().getAttribute("openid");//公众号微信openId
        if (org.apache.commons.lang.StringUtils.isNotEmpty(openid)) {
            order.setOpenId(openid);
        }
        order.setOs(param.getOs());
        order.setCreator(userId);
        return order;
    }

    /**
     * 生成订单编号
     *
     * @return
     */
    private String genOutTradeNo() {
        for (; ; ) {
            String outTradeNo = Constant.BOOK_ORDER_PREFIX + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
                    + RandomStringUtils.randomNumeric(11);
            if (findBy("payNum", outTradeNo) == null) {//由于该字段没有添加唯一性索引，需要确定是否重复
                return outTradeNo;
            }
        }
    }

    /**
     * 确认订单
     */
    @Override
    public BookCommidityDTO confirmOrder(Long comId) {

        BookCommidityDTO bookCommidityDTO = bookOrderMapper.confirmOrder(comId);

        if (null == bookCommidityDTO) {
            throw new CheckBookOrderParamException("商品已下架或商品不存在");
        }

        return bookCommidityDTO;
    }

    /**
     * 订单支付
     * @param payNum
     * @return
     */@Override
    public BookOrderPayDTO payOrder(String payNum,Users users) {
        if(payNum.equals("")&&payNum.equals(null)){
             return null;
        }
        BookOrderPayDTO bookOrderPayDTO = bookOrderMapper.payOrder(payNum,users.getId());
        return bookOrderPayDTO;
    }


}
