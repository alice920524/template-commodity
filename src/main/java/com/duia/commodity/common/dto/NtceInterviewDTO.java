package com.duia.commodity.common.dto;

/**
 * Created by zhenghui on 2018/3/29.
 */
public class NtceInterviewDTO {
    private String subject;
    private String passed; //通过
    private String examinee; //准考证
    private String batch; //考试批次
    private String province; //省份

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }

    public String getExaminee() {
        return examinee;
    }

    public void setExaminee(String examinee) {
        this.examinee = examinee;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
