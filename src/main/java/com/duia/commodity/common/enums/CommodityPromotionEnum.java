package com.duia.commodity.common.enums;

public enum CommodityPromotionEnum {

    RUSH_TYPE_TIME(2, "限制时间和数量"),
    RUSH_TYPE_NUM(1, "限制数量"),

    TYPE_SHARE(4, "分享购"),
    TYPE_RUSH(7, "抢购"),

    STATUS_INVALID(-1, "无效"),
    STATUS_NO_START(1, "未开始"),
    STATUS_END(2, "已结束"),
    STATUS_SELL_OUT(3, "售罄"),

    ACTIVITY_JOIN(1, "参与活动"),
    ACTIVITY_NO_JOIN(0, "不参与");


    private Integer key;
    private String value;

    CommodityPromotionEnum(Integer key, String value) {
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
