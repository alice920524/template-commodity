package com.duia.commodity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "aggrement_template")
public class AggrementTemplate implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 隶属SKU
     */
    private Integer sku;

    /**
     * 班型ID
     */
    @Column(name = "class_type_id")
    private Long classTypeId;

    /**
     * 协议类型[1:保险协议,2:保过协议,3:保质期协议,4:退款协议]
     */
    private Integer type;

    /**
     * 协议名称
     */
    private String title;

    /**
     * 所属公司
     */
    private String company;

    /**
     * 保险费用
     */
    private Double expense;

    /**
     * 有效性[0:无效,1:有效]
     */
    private Integer validity;

    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改者
     */
    private Integer updator;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 协议内容
     */
    private String content;

    /**
     * 保险考期
     */
    @Column(name = "insurance_exam_dates")
    private String insuranceExamDates;

    /**
     *质保期类型
     */
    private Integer guaType;

    /**
     * 保险视频引导[1:cc  3:网易]
     * @return
     */
    private Integer videoType;

    /**
     * CC视频ID
     */
    private String ccVideoId;

    /**
     * 网易视频id
     */
    private String vcloudVideoId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取隶属SKU
     *
     * @return sku - 隶属SKU
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * 设置隶属SKU
     *
     * @param sku 隶属SKU
     */
    public void setSku(Integer sku) {
        this.sku = sku;
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
     * 获取协议类型[1:保险协议,2:保过协议,3:保质期协议,4:退款协议]
     *
     * @return type - 协议类型[1:保险协议,2:保过协议,3:保质期协议,4:退款协议]
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置协议类型[1:保险协议,2:保过协议,3:保质期协议,4:退款协议]
     *
     * @param type 协议类型[1:保险协议,2:保过协议,3:保质期协议,4:退款协议]
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取协议名称
     *
     * @return title - 协议名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置协议名称
     *
     * @param title 协议名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取所属公司
     *
     * @return company - 所属公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所属公司
     *
     * @param company 所属公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取保险费用
     *
     * @return expense - 保险费用
     */
    public Double getExpense() {
        return expense;
    }

    /**
     * 设置保险费用
     *
     * @param expense 保险费用
     */
    public void setExpense(Double expense) {
        this.expense = expense;
    }

    /**
     * 获取有效性[0:无效,1:有效]
     *
     * @return validity - 有效性[0:无效,1:有效]
     */
    public Integer getValidity() {
        return validity;
    }

    /**
     * 设置有效性[0:无效,1:有效]
     *
     * @param validity 有效性[0:无效,1:有效]
     */
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Integer creator) {
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
    public Integer getUpdator() {
        return updator;
    }

    /**
     * 设置最后修改者
     *
     * @param updator 最后修改者
     */
    public void setUpdator(Integer updator) {
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

    /**
     * 获取协议内容
     *
     * @return content - 协议内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置协议内容
     *
     * @param content 协议内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getInsuranceExamDates() {
        return insuranceExamDates;
    }

    public void setInsuranceExamDates(String insuranceExamDates) {
        this.insuranceExamDates = insuranceExamDates;
    }

    public Integer getGuaType() {
        return guaType;
    }

    public void setGuaType(Integer guaType) {
        this.guaType = guaType;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getCcVideoId() {
        return ccVideoId;
    }

    public void setCcVideoId(String ccVideoId) {
        this.ccVideoId = ccVideoId;
    }

    public String getVcloudVideoId() {
        return vcloudVideoId;
    }

    public void setVcloudVideoId(String vcloudVideoId) {
        this.vcloudVideoId = vcloudVideoId;
    }
}