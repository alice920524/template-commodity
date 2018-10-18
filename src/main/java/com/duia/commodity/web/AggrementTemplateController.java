package com.duia.commodity.web;

import com.duia.commodity.service.AggrementTemplateService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hero on 2018/5/15.
 */
@RestController
@RequestMapping("/aggrementTemplate")
public class AggrementTemplateController {
    private static Logger logger =  Logger.getLogger(AggrementTemplateController.class);
    @Autowired
    private AggrementTemplateService aggrementTemplateService;
    /*
     * 根据班型id和保险类型，查询协议相关的内容以及质保期类型
     * */
    @PostMapping("/findByClassTypeIdAndType")
    public Result findByClassTypeIdAndType(Long classTypeId, Integer type, @RequestParam(value = "guaType",defaultValue = "0") Integer guaType){
        return ResultGenerator.genSuccessResult(aggrementTemplateService.findByClassTypeIdAndType(classTypeId,type,guaType));
    }
    /**
     * 根据订单ID查询
     * */
    @PostMapping("/findByOrderDetailId")
    public Result findByOrderDetailId(Long orderDetailId,Integer type){
        return ResultGenerator.genSuccessResult(aggrementTemplateService.findByOrderDetailId(orderDetailId, type));
    }
}
