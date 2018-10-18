package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "class_student_book")
public class ClassStudentBook {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 图书ID
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 订单详情id
     */
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    /**
     * 图书名称
     */
    private String title;

    /**
     * 调整价格
     */
    @Column(name = "cost_price")
    private Double costPrice;

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
     * 状态[0:未支付,1:已支付]
     */
    private Integer status;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取图书ID
     *
     * @return book_id - 图书ID
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置图书ID
     *
     * @param bookId 图书ID
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单详情id
     *
     * @return order_detail_id - 订单详情id
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * 设置订单详情id
     *
     * @param orderDetailId 订单详情id
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * 获取图书名称
     *
     * @return title - 图书名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置图书名称
     *
     * @param title 图书名称
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

    /**
     * 获取状态[0:未支付,1:已支付]
     *
     * @return status - 状态[0:未支付,1:已支付]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:未支付,1:已支付]
     *
     * @param status 状态[0:未支付,1:已支付]
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}