package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "book_relation")
public class BookRelation {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联id
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 关联类型[0:班型,5：图书商品,6:班级升级]
     */
    @Column(name = "relation_type")
    private Integer relationType;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 调整价格
     */
    @Column(name = "adjust_price")
    private Double adjustPrice;

    /**
     * 删除状态[0:未删除，1:已删除]
     */
    private Integer deleted;

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
     * 获取关联id
     *
     * @return relation_id - 关联id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置关联id
     *
     * @param relationId 关联id
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取关联类型[0:班型,5：图书商品,6:班级升级]
     *
     * @return relation_type - 关联类型[0:班型,5：图书商品,6:班级升级]
     */
    public Integer getRelationType() {
        return relationType;
    }

    /**
     * 设置关联类型[0:班型,5：图书商品,6:班级升级]
     *
     * @param relationType 关联类型[0:班型,5：图书商品,6:班级升级]
     */
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
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
     * 获取调整价格
     *
     * @return adjust_price - 调整价格
     */
    public Double getAdjustPrice() {
        return adjustPrice;
    }

    /**
     * 设置调整价格
     *
     * @param adjustPrice 调整价格
     */
    public void setAdjustPrice(Double adjustPrice) {
        this.adjustPrice = adjustPrice;
    }

    /**
     * 获取删除状态[0:未删除，1:已删除]
     *
     * @return deleted - 删除状态[0:未删除，1:已删除]
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除状态[0:未删除，1:已删除]
     *
     * @param deleted 删除状态[0:未删除，1:已删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}