package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.model.Commodity;
import com.duia.commodity.model.CommoditySpecial;

import java.util.List;

/**
 * Created by 李恒名 on 2017/7/13.
 */
public class CommoditySpecialDTO extends CommoditySpecial{
    private List<Commodity> commodityList;

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
