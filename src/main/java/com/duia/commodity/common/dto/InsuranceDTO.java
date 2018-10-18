package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.enums.OrderEnum;

/**
 * Created by 李恒名 on 2017/7/15.
 */
public class InsuranceDTO {
    private Long id;
    private String name;
    private String content;
    private Double price;
    // 保险选中状态
    private Integer checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * @Description:保险是否选中
     * @Date: 13:53 2018/5/15
     */
    public boolean insuranceIsChecked() {
        return OrderEnum.INSURANCE_CHECKED.getKey().equals(checked);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
