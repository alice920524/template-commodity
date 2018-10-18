package com.duia.commodity.common.dto;

import com.duia.commodity.model.AggrementTemplate;

import java.util.List;

public class CommodityOrderDTO {
    // 直播商品信息
    private List<CommodityDTO> commodityDTOS;
    // 加价购商品
    private List<CommodityDTO> addMoneyCommodityDTOS;
    // 保险
    private List<AggrementTemplate> insuranceList;
    // 订单信息
    private OrderParamDTO param;

    public CommodityOrderDTO() {
    }

    public CommodityOrderDTO(List<CommodityDTO> commodityDTOS, List<CommodityDTO> addMoneyCommodityDTOS, List<AggrementTemplate> insuranceList,OrderParamDTO param) {
        this.commodityDTOS = commodityDTOS;
        this.addMoneyCommodityDTOS = addMoneyCommodityDTOS;
        this.insuranceList = insuranceList;
        this.param = param;
    }

    public List<CommodityDTO> getCommodityDTOS() {
        return commodityDTOS;
    }

    public void setCommodityDTOS(List<CommodityDTO> commodityDTOS) {
        this.commodityDTOS = commodityDTOS;
    }

    public List<CommodityDTO> getAddMoneyCommodityDTOS() {
        return addMoneyCommodityDTOS;
    }

    public void setAddMoneyCommodityDTOS(List<CommodityDTO> addMoneyCommodityDTOS) {
        this.addMoneyCommodityDTOS = addMoneyCommodityDTOS;
    }

    public List<AggrementTemplate> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<AggrementTemplate> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public OrderParamDTO getParam() {
        return param;
    }

    public void setParam(OrderParamDTO param) {
        this.param = param;
    }
}
