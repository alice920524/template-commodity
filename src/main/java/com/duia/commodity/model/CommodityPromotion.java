package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "commodity_promotion")
public class CommodityPromotion {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * sku
     */
    private Integer sku;

    /**
     * 商品id
     */
    @Column(name = "com_id")
    private Long comId;

    /**
     * 商品类型[6:直播课]
     */
    @Column(name = "com_type")
    private Integer comType;

    /**
     * 班型ID
     */
    @Column(name = "class_type_id")
    private Long classTypeId;

    /**
     * 促销类型[4:分享购,5:抢购,6:拼团]
     */
    private Integer type;
    /**
     * 抢购类型[0:不限,1:限制数量,2:限制时间]
     * */
    @Column(name = "rush_type")
    private Integer rushType;
    /**
     * 数量上限[-1：不限制]'
     * */
    @Column(name = "maximum")
    private Integer maximum;
    /**
     * 销量
     * */
    @Column(name = "sales")
    private Integer sales;
    /**
     * 收费类型[0:免费,1:收费]
     * */
    @Column(name = "charge")
    private Integer charge;
    /**
     * 促销价格
     */
    @Column(name = "cost_price")
    private Double costPrice;

    /**
     * 活动开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 活动结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 状态[0:下架,1:上架]
     */
    private Integer status;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改者
     */
    private Long updator;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 一类质保期活动价
     */
    @Column(name = "first_price")
    private Double firstPrice;

    /**
     * 二类质保期活动价
     */
    @Column(name = "second_price")
    private Double secondPrice;

    /**
     * 班型类型[0:系统班,1:专题课]
     * */
    @Column(name = "course_type")
    private Integer courseType;

    /**
     * 是否多质保期[0:单质保期,1:多质保期]
     * */
    @Column(name = "gua_mul")
    private Integer guaMul;

    /**
     * 一类质保期是否参与活动[0:不参与,1:参与]
     * */
    @Column(name = "first_pro")
    private Integer firstPro;

    /**
     * 一类质保期是否参与活动[0:不参与,1:参与]
     * */
    @Column(name = "second_pro")
    private Integer secondPro;

    /**
     * 一类质保期活动价
     */
    public Double getFirstPrice() {
        return firstPrice;
    }

    /**
     * 二类质保期活动价
     */
    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取sku
     *
     * @return sku - sku
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * 设置sku
     *
     * @param sku sku
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

    /**
     * 获取商品id
     *
     * @return com_id - 商品id
     */
    public Long getComId() {
        return comId;
    }

    /**
     * 设置商品id
     *
     * @param comId 商品id
     */
    public void setComId(Long comId) {
        this.comId = comId;
    }

    /**
     * 获取商品类型[6:直播课]
     *
     * @return com_type - 商品类型[6:直播课]
     */
    public Integer getComType() {
        return comType;
    }

    /**
     * 设置商品类型[6:直播课]
     *
     * @param comType 商品类型[6:直播课]
     */
    public void setComType(Integer comType) {
        this.comType = comType;
    }

    /**
     * 获取班型ID
     *
     * @return class_type_id - 班型ID
     */
    public Long getClassTypeId() {
        return classTypeId;
    }

    /**
     * 设置班型ID
     *
     * @param classTypeId 班型ID
     */
    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    /**
     * 获取促销类型[4:分享购,5:抢购,6:拼团]
     *
     * @return type - 促销类型[4:分享购,5:抢购,6:拼团]
     */
    public Integer getType() {
        return type;
    }


    /**
     * 设置促销类型[4:分享购,5:抢购,6:拼团]
     *
     * @param type 促销类型[4:分享购,5:抢购,6:拼团]
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRushType() {
        return rushType;
    }

    public void setRushType(Integer rushType) {
        this.rushType = rushType;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    /**
     * 获取促销价格
     *
     * @return cost_price - 促销价格
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * 设置促销价格
     *
     * @param costPrice 促销价格
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取活动开始时间
     *
     * @return start_date - 活动开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置活动开始时间
     *
     * @param startDate 活动开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取活动结束时间
     *
     * @return end_date - 活动结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置活动结束时间
     *
     * @param endDate 活动结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取状态[0:下架,1:上架]
     *
     * @return status - 状态[0:下架,1:上架]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:下架,1:上架]
     *
     * @param status 状态[0:下架,1:上架]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
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
     * 获取最后修改者
     *
     * @return updator - 最后修改者
     */
    public Long getUpdator() {
        return updator;
    }

    /**
     * 设置最后修改者
     *
     * @param updator 最后修改者
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getGuaMul() {
        return guaMul;
    }

    public void setGuaMul(Integer guaMul) {
        this.guaMul = guaMul;
    }

    public Integer getFirstPro() {
        return firstPro;
    }

    public void setFirstPro(Integer firstPro) {
        this.firstPro = firstPro;
    }

    public Integer getSecondPro() {
        return secondPro;
    }

    public void setSecondPro(Integer secondPro) {
        this.secondPro = secondPro;
    }
}