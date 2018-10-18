package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "book_order_detail")
public class BookOrderDetail {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 图书商品名称
     */
    private String title;

    /**
     * 调整价格
     */
    @Column(name = "cost_price")
    private Double costPrice;

    /**
     * 零售价格
     */
    @Column(name = "real_price")
    private Double realPrice;

    /**
     * 业绩价
     */
    @Column(name = "performance_price")
    private String performancePrice;

    /**
     * 金蝶图书Id
     */
    @Column(name = "kingdee_id")
    private Integer kingdeeId;

    /**
     * 成本价
     */
    @Column(name = "kingdee_price")
    private String kingdeePrice;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取图书id
     *
     * @return book_id - 图书id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置图书id
     *
     * @param bookId 图书id
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取图书商品名称
     *
     * @return title - 图书商品名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置图书商品名称
     *
     * @param title 图书商品名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取调整价格
     *
     * @return cost_price - 调整价格
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * 设置调整价格
     *
     * @param costPrice 调整价格
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取零售价格
     *
     * @return real_price - 零售价格
     */
    public Double getRealPrice() {
        return realPrice;
    }

    /**
     * 设置零售价格
     *
     * @param realPrice 零售价格
     */
    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 获取业绩价
     *
     * @return performance_price - 业绩价
     */
    public String getPerformancePrice() {
        return performancePrice;
    }

    /**
     * 设置业绩价
     *
     * @param performancePrice 业绩价
     */
    public void setPerformancePrice(String performancePrice) {
        this.performancePrice = performancePrice;
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
    public String getKingdeePrice() {
        return kingdeePrice;
    }

    /**
     * 设置成本价
     *
     * @param kingdeePrice 成本价
     */
    public void setKingdeePrice(String kingdeePrice) {
        this.kingdeePrice = kingdeePrice;
    }
}