package com.duia.commodity.model;

import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Commodity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "real_price")
    private Double realPrice;

    /**
     * 0：组合商品；
     * 1：直播课；
     * 2：书；
     * 3：视频下载服务；
     * 4：答疑；
     * 5：题库
     */
    private Integer type;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "course_type")
    private Integer courseType;

    private Long creator;

    @Column(name = "create_time")
    private Date createTime;

    private Long updator;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 小班私教总人数
     */
    @Column(name = "count_num")
    private Integer countNum;

    /**
     * 小班私教购买的人数
     */
    @Column(name = "current_num")
    private Integer currentNum;

    /**
     * 上下架状态
     */
    private Integer status;

    /**
     * 基数
     */
    private Integer base;

    /**
     * 基数
     */
    private String description;

    /**
     * 班级号
     */
    @Column(name = "class_no")
    private String classNo;

    /**
     * 上架时间
     */
    @Column(name = "shelf_on_time")
    private Date shelfOnTime;

    /**
     * 下架时间
     */
    @Column(name = "shelf_off_time")
    private Date shelfOffTime;

    /**
     * 排序
     */
    private Integer sort;

    @Column(name = "qq_num")
    private String qqNum;

    /**
     * 邮寄地址标识[1,2]
     */
    @Column(name = "address_mark")
    private Integer addressMark;

    /**
     * vip有效标识[1有效,2无效]
     */
    @Column(name = "vip_mark")
    private Integer vipMark;

    /**
     * 班级有效期(月)
     */
    private Integer validity;

    /**
     * 视频商品对应的课程名称
     */
    @Column(name = "course_name")
    private String courseName;


    /**
     * 班型ID
     */
    private Long classTypeId;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return cost_price
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return real_price
     */
    public Double getRealPrice() {
        return realPrice;
    }

    /**
     * @param realPrice
     */
    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 获取0：组合商品；
     * 1：直播课；
     * 2：书；
     * 3：视频下载服务；
     * 4：答疑；
     * 5：题库
     *
     * @return type - 0：组合商品；
     * 1：直播课；
     * 2：书；
     * 3：视频下载服务；
     * 4：答疑；
     * 5：题库
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0：组合商品；
     * 1：直播课；
     * 2：书；
     * 3：视频下载服务；
     * 4：答疑；
     * 5：题库
     *
     * @param type 0：组合商品；
     *             1：直播课；
     *             2：书；
     *             3：视频下载服务；
     *             4：答疑；
     *             5：题库
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return cover_url
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * @param coverUrl
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * @return sku_id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    /**
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updator
     */
    public Long getUpdator() {
        return updator;
    }

    /**
     * @param updator
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取小班私教总人数
     *
     * @return count_num - 小班私教总人数
     */
    public Integer getCountNum() {
        return countNum;
    }

    /**
     * 设置小班私教总人数
     *
     * @param countNum 小班私教总人数
     */
    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    /**
     * 获取小班私教购买的人数
     *
     * @return current_num - 小班私教购买的人数
     */
    public Integer getCurrentNum() {
        return currentNum;
    }

    /**
     * 设置小班私教购买的人数
     *
     * @param currentNum 小班私教购买的人数
     */
    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    /**
     * 获取上下架状态
     *
     * @return status - 上下架状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置上下架状态
     *
     * @param status 上下架状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取基数
     *
     * @return base - 基数
     */
    public Integer getBase() {
        return base;
    }

    /**
     * 设置基数
     *
     * @param base 基数
     */
    public void setBase(Integer base) {
        this.base = base;
    }

    /**
     * 获取基数
     *
     * @return description - 基数
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置基数
     *
     * @param description 基数
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取班级号
     *
     * @return class_no - 班级号
     */
    public String getClassNo() {
        return classNo;
    }

    /**
     * 设置班级号
     *
     * @param classNo 班级号
     */
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    /**
     * 获取上架时间
     *
     * @return shelf_on_time - 上架时间
     */
    public Date getShelfOnTime() {
        return shelfOnTime;
    }

    /**
     * 设置上架时间
     *
     * @param shelfOnTime 上架时间
     */
    public void setShelfOnTime(Date shelfOnTime) {
        this.shelfOnTime = shelfOnTime;
    }

    /**
     * 获取下架时间
     *
     * @return shelf_off_time - 下架时间
     */
    public Date getShelfOffTime() {
        return shelfOffTime;
    }

    /**
     * 设置下架时间
     *
     * @param shelfOffTime 下架时间
     */
    public void setShelfOffTime(Date shelfOffTime) {
        this.shelfOffTime = shelfOffTime;
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
     * @return qq_num
     */
    public String getQqNum() {
        return qqNum;
    }

    /**
     * @param qqNum
     */
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    /**
     * 获取邮寄地址标识[1,2]
     *
     * @return address_mark - 邮寄地址标识[1,2]
     */
    public Integer getAddressMark() {
        return addressMark;
    }

    /**
     * 设置邮寄地址标识[1,2]
     *
     * @param addressMark 邮寄地址标识[1,2]
     */
    public void setAddressMark(Integer addressMark) {
        this.addressMark = addressMark;
    }

    /**
     * 获取vip有效标识[1有效,2无效]
     *
     * @return vip_mark - vip有效标识[1有效,2无效]
     */
    public Integer getVipMark() {
        return vipMark;
    }

    /**
     * 设置vip有效标识[1有效,2无效]
     *
     * @param vipMark vip有效标识[1有效,2无效]
     */
    public void setVipMark(Integer vipMark) {
        this.vipMark = vipMark;
    }

    /**
     * 获取班级有效期(月)
     *
     * @return validity - 班级有效期(月)
     */
    public Integer getValidity() {
        return validity;
    }

    /**
     * 设置班级有效期(月)
     *
     * @param validity 班级有效期(月)
     */
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    /**
     * 获取视频商品对应的课程名称
     *
     * @return course_name - 视频商品对应的课程名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置视频商品对应的课程名称
     *
     * @param courseName 视频商品对应的课程名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}