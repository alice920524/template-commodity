package com.duia.commodity.common.enums;

public enum OrderDetailEnum {
    // 教材类型[0:普通,1:初中,2:高中]
    BOOK_TYPE_COMMON(0, "普通"),
    BOOK_TYPE_JUNIOR_SCHOOL(1, "初中"),
    BOOK_TYPE_HIGH_SCHOOL(2, "高中");

    private Integer key;
    private String value;

    OrderDetailEnum(Integer key, String value) {
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
