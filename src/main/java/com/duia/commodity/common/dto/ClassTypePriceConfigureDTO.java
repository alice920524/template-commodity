package com.duia.commodity.common.dto;

public class ClassTypePriceConfigureDTO {
    // 标题
    private String title;
    // 价格
    private Double price;
    // 延保[0:否,1:是]
    private Integer gua;
    // 延期[0:否,1:是]
    private Integer delay;

    public ClassTypePriceConfigureDTO() {
    }

    public ClassTypePriceConfigureDTO(String title, Double price, Integer gua, Integer delay) {
        this.title = title;
        this.price = price;
        this.gua = gua;
        this.delay = delay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getGua() {
        return gua;
    }

    public void setGua(Integer gua) {
        this.gua = gua;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }
}
