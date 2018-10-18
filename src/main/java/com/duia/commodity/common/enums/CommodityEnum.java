package com.duia.commodity.common.enums;

/**
 * Created by Hero on 2018/5/14.
 * 定义枚举类 命名格式：表名_* 或者 组件_*
 */
public enum CommodityEnum {
    /*前端传参商品类型*/
    COMMODITY_TYPE_SINGLE(0, "单品"),
    COMMODITY_TYPE_COMBO(1, "套餐"),
    COMMODITY_TYPE_COMBINATION(2, "组合"),

    COMMODITY_VALID(1, "上架"),
    COMMODITY_INVALID(0, "下架");

    private Integer key;
    private String value;

    CommodityEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
