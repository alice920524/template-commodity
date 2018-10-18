package com.duia.commodity.common.enums;

/**
 * Created by Hero on 2018/5/14.
 * 定义枚举类 命名格式：表名_* 或者 组件_*
 */
public enum OrderEnum {
    /*保险相关的组件枚举*/
    INSURANCE_UN_CHECKED(0, "未选中"),
    INSURANCE_CHECKED(1, "选中"),

    ORDER_TYPE_COMMON(0, "普通"),
    ORDER_TYPE_PARENT(1, "父订单"),
    ORDER_TYPE_SUB(2, "子订单"),

    VOUCHER_ORDER_PT(0, "普通"),
    VOUCHER_ORDER_YHTQ(1, "特权订单"),

    BUY_TYPE_COMMON(0, "正常购买"),
    BUY_TYPE_ADD_MONEY(1, "加价购"),

    MAIL(1, "需要邮寄"),
    MAIL_CLASS_TYPE_NO(2, "班型不需要"),
    MAIL_STUDENT_NO(0, "学员不需要"),

    // 20170321前为老订单
    OLD_ORDER(1, "老订单"),
    NEW_ORDER(2, "新订单"),

    TURN_ORDER_YES(1, "转订单"),
    TURN_ORDER_NO(2, "非转订单"),

    ORDER_DELETE_NO(0, "未删除"),
    ORDER_DELETE_YES(1, "已删除"),
    ORDER_CANCEL(2, "已取消"),

    VOUCHER_ORDER_NO(0, "非特权订单"),
    VOUCHER_ORDER_YES(1, "特权订单"),

    BUY_MODE_COMMON(0, "普通购"),
    BUY_MODE_ZERO(1, "零元购"),
    BUY_MODE_SHARE(2, "分享购"),

    INSTAL_BATCH_MULTI_DEFAULT(0, "分批、期、多凭证初始值");

    private Integer key;
    private String value;

    OrderEnum(Integer key, String value) {
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
