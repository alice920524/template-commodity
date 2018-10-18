package com.duia.commodity.common.util;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.CommodityBaseDTO;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.dto.CommodityExtraDTO;
import com.duia.commodity.model.CommodityPromotion;

/**
 * @Author: zhangchenxu
 * @Description:
 * @Date: 18:39 2018/7/18
 */
public abstract class OrderPriceUtil {
    /**
     * 获取一类价格
     * */
    public static Double getFirstPrice(CommodityBaseDTO commodityBaseDTO) {
        if (null == commodityBaseDTO) {
            return 0.0;
        }
        return commodityBaseDTO.getFirstPrice();
    }

    /**
     * 根据类型获取一类价格
     * */
    public static Double getCommodityPrice(CommodityDTO commodityDTO, Integer guaType) {
        if (null==commodityDTO) {
            return 0.0;
        }

        if (guaType == null || Constant.GUA_TYPE_COMMON.equals(guaType)) {
            return commodityDTO.getPrice();
        } else if (Constant.GUA_TYPE_FIRST.equals(guaType)) {
            return commodityDTO.getFirstPrice();
        } else if (Constant.GUA_TYPE_SECOND.equals(guaType)) {
            return commodityDTO.getSecondPrice();
        }
        return 0.0;
    }

    public static Double getCommodityPrice(CommodityPromotion commodityPromotion, Integer guaType) {
        if (null==commodityPromotion) {
            return 0.0;
        }

        if (guaType == null || Constant.GUA_TYPE_COMMON.equals(guaType)) {
            return commodityPromotion.getCostPrice();
        } else if (Constant.GUA_TYPE_FIRST.equals(guaType)) {
            return commodityPromotion.getFirstPrice();
        } else if (Constant.GUA_TYPE_SECOND.equals(guaType)) {
            return commodityPromotion.getSecondPrice();
        }
        return 0.0;
    }

    public static Double getAddMoneyCommodityPrice(CommodityDTO commodityDTO, Integer guaType) {
        if (null==commodityDTO) {
            return 0.0;
        }

        if (guaType == null || Constant.GUA_TYPE_COMMON.equals(guaType)) {
            return commodityDTO.getRealPrice();
        } else if (Constant.GUA_TYPE_FIRST.equals(guaType)) {
            return commodityDTO.getFirstPrice();
        } else if (Constant.GUA_TYPE_SECOND.equals(guaType)) {
            return commodityDTO.getSecondPrice();
        }
        return 0.0;
    }

    public static Double getCommodityExtraPrice(CommodityExtraDTO commodityExtraDTO, Integer guaType) {
        if (null==commodityExtraDTO) {
            return 0.0;
        }

        if (guaType == null || Constant.GUA_TYPE_COMMON.equals(guaType)) {
            return commodityExtraDTO.getCostPrice();
        } else if (Constant.GUA_TYPE_FIRST.equals(guaType)) {
            return commodityExtraDTO.getFirstPrice();
        } else if (Constant.GUA_TYPE_SECOND.equals(guaType)) {
            return commodityExtraDTO.getSecondPrice();
        }
        return 0.0;
    }


}
