package com.duia.commodity.web;

import com.duia.commodity.common.dto.DiscountResultDTO;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.PayDiscountDetailService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by 李恒名 on 2017/7/18.
 */
@RestController
@RequestMapping("/discount")
public class DiscountController {
    private static Logger logger = Logger.getLogger(DiscountController.class);
    @Resource
    private PayDiscountDetailService payDiscountDetailService;


    /**
     * 优惠券列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Result discountList(@SessionAttribute Users user, Integer appType, Long comId, Long specialId, Integer status, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {

        DiscountResultDTO discountResultDTO = this.payDiscountDetailService.findDiscountList(user.getId(), appType, comId, specialId, status, page, size);

        return ResultGenerator.genSuccessResult(discountResultDTO);
    }

}
