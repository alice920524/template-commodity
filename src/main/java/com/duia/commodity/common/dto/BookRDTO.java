package com.duia.commodity.common.dto;

/**
 * Created by 李国勇 on 2018/6/25.
 */
public class BookRDTO {

    private Long id;
    private Integer sku;
    private Integer kingdeeId; //金蝶图书Id
    private Double kingdeePrice; //成本价
    private String kingdeeName; //金蝶图书名称
    private String bookCode; //图书编码
    private String bookName; //对啊图书名称
    private Double costPrice; //销售价[零售价]
    private Double performancePrice; //业绩价
    private Double adjustPrice; //调整价格

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getKingdeeId() {
        return kingdeeId;
    }

    public void setKingdeeId(Integer kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public Double getKingdeePrice() {
        return kingdeePrice;
    }

    public void setKingdeePrice(Double kingdeePrice) {
        this.kingdeePrice = kingdeePrice;
    }

    public String getKingdeeName() {
        return kingdeeName;
    }

    public void setKingdeeName(String kingdeeName) {
        this.kingdeeName = kingdeeName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getPerformancePrice() {
        return performancePrice;
    }

    public void setPerformancePrice(Double performancePrice) {
        this.performancePrice = performancePrice;
    }

    public Double getAdjustPrice() {
        return adjustPrice;
    }

    public void setAdjustPrice(Double adjustPrice) {
        this.adjustPrice = adjustPrice;
    }
}
