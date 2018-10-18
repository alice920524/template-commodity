package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "class_type_deadline")
public class ClassTypeDeadline {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 班型ID
     */
    @Column(name = "class_type_id")
    private Long classTypeId;

    /**
     * 年月[201701]
     */
    @Column(name = "use_date")
    private String useDate;

    /**
     * 截止日期
     */
    private Date deadline;

    /**
     * 最后修改者
     */
    private Long updator;

    /**
     * 记录最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 质保期类型[0:普通,1:1类,2:2类]
     */
    @Column(name = "gua_type")
    private Integer guaType;

    /**
     * 延1
     */
    @Column(name = "delay_first")
    private Date delayFirst;

    /**
     * 延2
     */
    @Column(name = "delay_second")
    private Date delaySecond;

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
     * 获取年月[201701]
     *
     * @return use_date - 年月[201701]
     */
    public String getUseDate() {
        return useDate;
    }

    /**
     * 设置年月[201701]
     *
     * @param useDate 年月[201701]
     */
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    /**
     * 获取截止日期
     *
     * @return deadline - 截止日期
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * 设置截止日期
     *
     * @param deadline 截止日期
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
     * 获取记录最后修改时间
     *
     * @return update_time - 记录最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置记录最后修改时间
     *
     * @param updateTime 记录最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getGuaType() {
        return guaType;
    }

    public void setGuaType(Integer guaType) {
        this.guaType = guaType;
    }

    public Date getDelayFirst() {
        return delayFirst;
    }

    public void setDelayFirst(Date delayFirst) {
        this.delayFirst = delayFirst;
    }

    public Date getDelaySecond() {
        return delaySecond;
    }

    public void setDelaySecond(Date delaySecond) {
        this.delaySecond = delaySecond;
    }
}