package com.duia.commodity.web;

import com.duia.commodity.common.dto.BookCommidityDTO;
import com.duia.commodity.common.dto.BookOrderParamDTO;
import com.duia.commodity.common.exception.CheckBookOrderParamException;
import com.duia.commodity.model.BookOrder;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.BookOrderService;
import com.duia.commodity.service.BookOrderTransService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import com.duia.security.decrypt.Dec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李国勇 on 2018/6/25.
 */
@RestController
@RequestMapping("/bookOrder")
public class BookOrderController {

    @Autowired
    private BookOrderService bookOrderService;
    @Autowired
    private BookOrderTransService bookOrderTransService;

    /**
     * 创建订单
     *
     * @param request
     * @param
     * @return
     */
    @PostMapping("/create")
    public Result commodityOrder(HttpServletRequest request, @SessionAttribute Users user, String p) {

        //加密
        BookOrderParamDTO bookOrderParamDTO = Dec.P(BookOrderParamDTO.class, p);
        try {
            return ResultGenerator.genSuccessResult(bookOrderService.create(request, user, bookOrderParamDTO));
        }catch (CheckBookOrderParamException exception){
            return ResultGenerator.genFailResult(exception.getMessage());
        }
    }

    /**
     * 更新订单支付方式,给app用
     *
     * @param id
     * @param payType
     * @return
     */
    @PostMapping("/updatePayTypeById")
    public Result updatePayTypeById(@RequestParam Long id, @RequestParam String payType) {
        if (null == id || StringUtils.isEmpty(payType)) {
            return ResultGenerator.genFailResult("参数不能为空");
        }
        BookOrder bookOrder = bookOrderService.findById(id);
        bookOrder.setPayType(payType);
        bookOrderService.update(bookOrder);
        bookOrderTransService.saveTrans(bookOrder);
        return ResultGenerator.genSuccessResult();
    }

    /**
     确认订单接口
     参数：商品id
     */
    @PostMapping("/confirmOrder")
    public  Result confirmOrder(@RequestParam String p){
        //加密
        BookOrderParamDTO paramDTO = Dec.P(BookOrderParamDTO.class, p);
        try {
            BookCommidityDTO bc = this.bookOrderService.confirmOrder(paramDTO.getComId());
            return  ResultGenerator.genSuccessResult(bc);
        } catch (CheckBookOrderParamException exception) {
            return ResultGenerator.genFailResult(exception.getMessage());
        }

    }

    /**
     * 订单支付
     * 通过订单编号和userId查询商品名称和实付价格以及支付状态
     */
    @PostMapping("/orderPay")
    public  Result orderPay(@RequestParam String payNum, @SessionAttribute Users user){
        return ResultGenerator.genSuccessResult(this.bookOrderService.payOrder(payNum,user));

    }
}
