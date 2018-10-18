package com.duia.commodity.web;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.CommodityExtraDTO;
import com.duia.commodity.common.util.WebUtil;
import com.duia.commodity.service.CommodityExtraBuyingRelationService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/extra/commodity")
public class CommodityExtraBuyingController {
    private static Logger logger =  Logger.getLogger(CommodityExtraBuyingController.class);
    @Resource
    private CommodityExtraBuyingRelationService commodityExtraBuyingRelationService;

    /**
     * 商品加价购列表
     * @param request
     * @param comId 商品id
     * @param comType 商品类型(0：单品；1：套餐；2：组合) ,查询数据需要处理数据库(0：单品；1：套餐；2：组合)
     * @param appType 终端类型
     * @param specialId 套餐或组合商品id
     * */
    @PostMapping("/list")
    @ResponseBody
    public Result saleCommodityList(@RequestParam Long comId,
                                    @RequestParam Integer comType,
                                    Integer appType,
                                    Long specialId,
                                    HttpServletRequest request) {
        if (appType == null) {
            appType = WebUtil.isWap(request) ? Constant.WAP_TYPE : Constant.WEB_TYPE;
        }
        List<CommodityExtraDTO> commodityList = commodityExtraBuyingRelationService.findCommodityExtra(comId,specialId, comType, appType);
        return ResultGenerator.genSuccessResult(commodityList);
    }
}
