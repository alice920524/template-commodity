package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "book_commodity")
public class BookCommodity {
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
     * 商品价格
     */
    private Double price;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品副标题
     */
    private String subtitle;

    /**
     * 商品封面
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 资源包id
     */
    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * 状态[0:未上架,1:上架]
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private Long creator;

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
     * 内容详情
     */
    private String content;

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
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取商品名称
     *
     * @return title - 商品名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品名称
     *
     * @param title 商品名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品副标题
     *
     * @return subtitle - 商品副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置商品副标题
     *
     * @param subtitle 商品副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取商品封面
     *
     * @return cover_url - 商品封面
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置商品封面
     *
     * @param coverUrl 商品封面
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * 获取资源包id
     *
     * @return resource_id - 资源包id
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源包id
     *
     * @param resourceId 资源包id
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取状态[0:未上架,1:上架]
     *
     * @return status - 状态[0:未上架,1:上架]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:未上架,1:上架]
     *
     * @param status 状态[0:未上架,1:上架]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(Long creator) {
        this.creator = creator;
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

    /**
     * 获取内容详情
     *
     * @return content - 内容详情
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容详情
     *
     * @param content 内容详情
     */
    public void setContent(String content) {
        this.content = content;
    }
}