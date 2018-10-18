package com.duia.commodity.common.enums;

public enum ClassTypeEnum {

    COURSE_TYPE_SYSTEM(0, "系统班"),
    COURSE_TYPE_SPECIAL(1, "专题课"),


    DISCOUNT_ZREO(0, "不可用"),
    DISCOUNT_ONE(1, "优惠码可用"),
    DISCOUNT_TWO(2, "优惠券可用"),

    VOUCHER_OPEN(1, "创建优惠特权开启"),
    VOUCHER_CLOSE(0, "创建优惠特权关闭"),

    BOOK_TYPE_COMMON(0, "普通"),

    FACE_RECOGNITION_OPEN(1,"人脸识别开启"),
    FACE_RECOGNITION_CLOSE(0,"人脸识别关闭"),

    CHARGE_NO(0, "免费"),
    CHARGE(1, "收费"),;

    private Integer key;
    private String value;

    ClassTypeEnum(Integer key, String value) {
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
