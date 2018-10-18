package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "commodity_extra_buying_relation")
public class CommodityExtraBuyingRelation {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联ID[单品-加价购表ID,套餐ID,组合ID]
     */
    @Column(name = "relation_id")
    private Integer relationId;

    /**
     * 加价购类型[0:单品,1:套餐,2:组合]
     */
    private Integer type;

    /**
     * 类别
     */
    private Integer sku;

    /**
     * 加价商品ID
     */
    @Column(name = "com_id")
    private Integer comId;

    /**
     * 班型ID
     */
    @Column(name = "class_type_id")
    private Integer classTypeId;

    /**
     * 加价金额
     */
    @Column(name = "extra_price")
    private Double extraPrice;

    /**
     * 学习包[1:无,2:有]
     */
    @Column(name = "address_mark")
    private Integer addressMark;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关联ID[单品-加价购表ID,套餐ID,组合ID]
     *
     * @return relation_id - 关联ID[单品-加价购表ID,套餐ID,组合ID]
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * 设置关联ID[单品-加价购表ID,套餐ID,组合ID]
     *
     * @param relationId 关联ID[单品-加价购表ID,套餐ID,组合ID]
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取加价购类型[0:单品,1:套餐,2:组合]
     *
     * @return type - 加价购类型[0:单品,1:套餐,2:组合]
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置加价购类型[0:单品,1:套餐,2:组合]
     *
     * @param type 加价购类型[0:单品,1:套餐,2:组合]
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取类别
     *
     * @return sku - 类别
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * 设置类别
     *
     * @param sku 类别
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

    /**
     * 获取加价商品ID
     *
     * @return com_id - 加价商品ID
     */
    public Integer getComId() {
        return comId;
    }

    /**
     * 设置加价商品ID
     *
     * @param comId 加价商品ID
     */
    public void setComId(Integer comId) {
        this.comId = comId;
    }

    /**
     * 获取班型ID
     *
     * @return class_type_id - 班型ID
     */
    public Integer getClassTypeId() {
        return classTypeId;
    }

    /**
     * 设置班型ID
     *
     * @param classTypeId 班型ID
     */
    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    /**
     * 获取加价金额
     *
     * @return extra_price - 加价金额
     */
    public Double getExtraPrice() {
        return extraPrice;
    }

    /**
     * 设置加价金额
     *
     * @param extraPrice 加价金额
     */
    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    /**
     * 获取学习包[1:无,2:有]
     *
     * @return address_mark - 学习包[1:无,2:有]
     */
    public Integer getAddressMark() {
        return addressMark;
    }

    /**
     * 设置学习包[1:无,2:有]
     *
     * @param addressMark 学习包[1:无,2:有]
     */
    public void setAddressMark(Integer addressMark) {
        this.addressMark = addressMark;
    }
}