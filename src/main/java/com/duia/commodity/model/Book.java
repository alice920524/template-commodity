package com.duia.commodity.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Book {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * SKU
     */
    private Integer sku;

    /**
     * 金蝶图书Id
     */
    @Column(name = "kingdee_id")
    private Integer kingdeeId;

    /**
     * 成本价
     */
    @Column(name = "kingdee_price")
    private Double kingdeePrice;

    /**
     * 金蝶图书名称
     */
    @Column(name = "kingdee_name")
    private String kingdeeName;

    /**
     * 图书编码
     */
    @Column(name = "book_code")
    private String bookCode;

    /**
     * 对啊图书名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 销售价[零售价]
     */
    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "s_cost_price")
    private Double sCostPrice;

    /**
     * 业绩价
     */
    @Column(name = "performance_price")
    private Double performancePrice;

    /**
     * 处理状态[0:未处理,1：已处理]
     */
    private Integer handle;

    /**
     * 状态[0:无效,1：有效]
     */
    private Integer status;

    /**
     * 同步时间
     */
    @Column(name = "synchronize_time")
    private Date synchronizeTime;

    /**
     * 同步人
     */
    private Long synchronizor;

    /**
     * 操作时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 操作人
     */
    private Long updator;

    /**
     * 获取PK
     *
     * @return id - PK
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置PK
     *
     * @param id PK
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取SKU
     *
     * @return sku - SKU
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * 设置SKU
     *
     * @param sku SKU
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

    /**
     * 获取金蝶图书Id
     *
     * @return kingdee_id - 金蝶图书Id
     */
    public Integer getKingdeeId() {
        return kingdeeId;
    }

    /**
     * 设置金蝶图书Id
     *
     * @param kingdeeId 金蝶图书Id
     */
    public void setKingdeeId(Integer kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    /**
     * 获取成本价
     *
     * @return kingdee_price - 成本价
     */
    public Double getKingdeePrice() {
        return kingdeePrice;
    }

    /**
     * 设置成本价
     *
     * @param kingdeePrice 成本价
     */
    public void setKingdeePrice(Double kingdeePrice) {
        this.kingdeePrice = kingdeePrice;
    }

    /**
     * 获取金蝶图书名称
     *
     * @return kingdee_name - 金蝶图书名称
     */
    public String getKingdeeName() {
        return kingdeeName;
    }

    /**
     * 设置金蝶图书名称
     *
     * @param kingdeeName 金蝶图书名称
     */
    public void setKingdeeName(String kingdeeName) {
        this.kingdeeName = kingdeeName;
    }

    /**
     * 获取图书编码
     *
     * @return book_code - 图书编码
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     * 设置图书编码
     *
     * @param bookCode 图书编码
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    /**
     * 获取对啊图书名称
     *
     * @return book_name - 对啊图书名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置对啊图书名称
     *
     * @param bookName 对啊图书名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取销售价[零售价]
     *
     * @return cost_price - 销售价[零售价]
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * 设置销售价[零售价]
     *
     * @param costPrice 销售价[零售价]
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getsCostPrice() {
        return sCostPrice;
    }

    public void setsCostPrice(Double sCostPrice) {
        this.sCostPrice = sCostPrice;
    }

    /**
     * 获取业绩价
     *
     * @return performance_price - 业绩价
     */
    public Double getPerformancePrice() {
        return performancePrice;
    }

    /**
     * 设置业绩价
     *
     * @param performancePrice 业绩价
     */
    public void setPerformancePrice(Double performancePrice) {
        this.performancePrice = performancePrice;
    }

    /**
     * 获取处理状态[0:未处理,1：已处理]
     *
     * @return handle - 处理状态[0:未处理,1：已处理]
     */
    public Integer getHandle() {
        return handle;
    }

    /**
     * 设置处理状态[0:未处理,1：已处理]
     *
     * @param handle 处理状态[0:未处理,1：已处理]
     */
    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    /**
     * 获取状态[0:无效,1：有效]
     *
     * @return status - 状态[0:无效,1：有效]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:无效,1：有效]
     *
     * @param status 状态[0:无效,1：有效]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取同步时间
     *
     * @return synchronize_time - 同步时间
     */
    public Date getSynchronizeTime() {
        return synchronizeTime;
    }

    /**
     * 设置同步时间
     *
     * @param synchronizeTime 同步时间
     */
    public void setSynchronizeTime(Date synchronizeTime) {
        this.synchronizeTime = synchronizeTime;
    }

    /**
     * 获取同步人
     *
     * @return synchronizor - 同步人
     */
    public Long getSynchronizor() {
        return synchronizor;
    }

    /**
     * 设置同步人
     *
     * @param synchronizor 同步人
     */
    public void setSynchronizor(Long synchronizor) {
        this.synchronizor = synchronizor;
    }

    /**
     * 获取操作时间
     *
     * @return update_time - 操作时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置操作时间
     *
     * @param updateTime 操作时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取操作人
     *
     * @return updator - 操作人
     */
    public Long getUpdator() {
        return updator;
    }

    /**
     * 设置操作人
     *
     * @param updator 操作人
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }
}