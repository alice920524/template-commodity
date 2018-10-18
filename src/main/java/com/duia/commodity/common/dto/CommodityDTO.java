package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.model.AggrementTemplate;
import com.duia.commodity.model.ClassType;

import java.util.Date;

/**
 * Created by 李恒名 on 2017/7/15.
 * <p>
 * 对应确认订单页面的课程信息内的商品
 */
public class CommodityDTO {
    private Long id;//商品ID
    private String name;//商品名稱
    private Date deadline; // 截止日期
    private Date deadline2; // 延2
    private Double price;//价格
    private Double firstPrice;//一类价格
    private Double secondPrice;//二类价格
    private Double realPrice;//商品原始价格
    private Double comCostPrice;// 订单详情入库实付价格
    private Double comRealPrice;// 订单详情入库商品原价
    private Integer addressMark;//是否有学习包标记 [1:没有，2:有]
    private Double bookPrice;//学习包价格
    private Integer bookType;//教材类型[0:普通,1:区分初高中]
    private Integer bookTypeChecked;// 教材选中类型[0:普通,1:初中,2:高中]
    private Long classTypeId;//班型ID
    private Integer insurance;//是否有保险，1有，0无
    private String coverUrl;//商品封面
    private String wapCoverUrl;//wap商品封面
    private Integer type;//商品TYPE
    private Long skuId;
    private Integer courseType;//班型类型
    private Integer guaMul;//多类型质保期[0:普通,1:多类型]
    private Integer guaType;
    private Integer mode;//有效期模式[0:月份,1:日期,2:天数]
    private Integer validity;//班级有效期(月,日)
    private Integer buyType;// 加价购商品
    private Integer comMode;//1: 组合购买 2:单买
    private Long specialId;// 套餐组合ID
    private AggrementTemplate aggrementTemplate;
    private ClassType classType;

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

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getBookTypeChecked() {
        return bookTypeChecked;
    }

    public void setBookTypeChecked(Integer bookTypeChecked) {
        this.bookTypeChecked = bookTypeChecked;
    }

    public Integer getAddressMark() {
        return addressMark;
    }

    public void setAddressMark(Integer addressMark) {
        this.addressMark = addressMark;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    public Integer getInsurance() {
        return insurance;
    }

    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getWapCoverUrl() {
        return wapCoverUrl;
    }

    public void setWapCoverUrl(String wapCoverUrl) {
        this.wapCoverUrl = wapCoverUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Double getComCostPrice() {
        return comCostPrice;
    }

    public void setComCostPrice(Double comCostPrice) {
        this.comCostPrice = comCostPrice;
    }

    public Double getComRealPrice() {
        return comRealPrice;
    }

    public void setComRealPrice(Double comRealPrice) {
        this.comRealPrice = comRealPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    public Integer getGuaMul() {
        return guaMul;
    }

    public void setGuaMul(Integer guaMul) {
        this.guaMul = guaMul;
    }

    public Date getDeadline2() {
        return deadline2;
    }

    public void setDeadline2(Date deadline2) {
        this.deadline2 = deadline2;
    }

    public Integer getGuaType() {
        return guaType;
    }

    public void setGuaType(Integer guaType) {
        this.guaType = guaType;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public AggrementTemplate getAggrementTemplate() {
        return aggrementTemplate;
    }

    public void setAggrementTemplate(AggrementTemplate aggrementTemplate) {
        this.aggrementTemplate = aggrementTemplate;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    public Integer getComMode() {
        return comMode;
    }

    public void setComMode(Integer comMode) {
        this.comMode = comMode;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
