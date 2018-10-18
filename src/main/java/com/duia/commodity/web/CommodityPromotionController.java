package com.duia.commodity.web;

import com.duia.commodity.common.enums.CommodityEnum;
import com.duia.commodity.service.CommodityPromotionService;
import com.duia.commodity.service.CommodityService;
import com.duia.enums.Sales;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class CommodityPromotionController {
    @Resource
    private CommodityPromotionService commodityPromotionService;
    @Resource
    private CommodityService commodityService;


    /**
     * 商品详情-抢购活动信息
     *
     * @Date: 17:22 2018/6/6
     */
    @PostMapping("/rushDetail")
    public Result commodityDetailInfo(Long comId, Integer appType) {
        // 商品上下架状态
        int commodityStatus = this.commodityService.queryCommodityStatus(comId, appType);

        if (CommodityEnum.COMMODITY_VALID.getKey().equals(commodityStatus)) {// 上架

            return ResultGenerator.genSuccessResult(commodityPromotionService.selectCommodityRushDetail(comId, appType));
        } else { // 下架

            return ResultGenerator.genSuccessResult(commodityPromotionService.selectInvalidLiveCommodityPromotion(comId, Sales.PANIC.getState(), appType));
        }
    }

    /**
     * 减销量
     *
     * @Date: 15:29 2018/6/13
     */
    @PostMapping(value = "/subSales")
    public Result subSales(@RequestBody List<Long> orderIds) {

        return this.commodityPromotionService.subSales(orderIds);
    }
}
