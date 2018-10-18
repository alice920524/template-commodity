package com.duia.commodity.common.enums;

public enum  ErrorResultCode {
    BALANCE_ERROR(701),// 余额不足
    DISCOUNT_CASE1(702),// 优惠特权不能用
    DISCOUNT_CASE2(703),// 开关未开启
    DISCOUNT_CASE3(704),// sku、商品ID不匹配
    DISCOUNT_CASE4(705),// 优惠券上限
    DISCOUNT_CASE5(706),// 零元购不允许使用
    DISCOUNT_CASE7(708);// 班型不匹配

    public int code;

    ErrorResultCode(int code) {
        this.code = code;
    }
}
