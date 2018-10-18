package com.duia.commodity.common.dto;

import java.util.Date;

// 质保期提示
public class ClassInfoTipsDTO {

    private String name;

    private Integer guaType;

    private Integer allowGua;

    private Date deadline;

    private Date delayFirst;

    private Date delaySecond;

    //是否有结课时间
    private Integer hasEndDate;

    //结课时间
    private Date classEnd;


    public ClassInfoTipsDTO() {
    }

    public ClassInfoTipsDTO(String name, Integer guaType, Integer allowGua, Date deadline, Date delayFirst, Date delaySecond) {
        this.name = name;
        this.guaType = guaType;
        this.allowGua = allowGua;
        this.deadline = deadline;
        this.delayFirst = delayFirst;
        this.delaySecond = delaySecond;
        this.hasEndDate = hasEndDate;
        this.classEnd = classEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGuaType() {
        return guaType;
    }

    public void setGuaType(Integer guaType) {
        this.guaType = guaType;
    }

    public Integer getAllowGua() {
        return allowGua;
    }

    public void setAllowGua(Integer allowGua) {
        this.allowGua = allowGua;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public Integer getHasEndDate() {
        return hasEndDate;
    }

    public void setHasEndDate(Integer hasEndDate) {
        this.hasEndDate = hasEndDate;
    }

    public Date getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(Date classEnd) {
        this.classEnd = classEnd;
    }
}
