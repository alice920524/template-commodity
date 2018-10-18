package com.duia.commodity.common.dto;

/**
 * Created by zhenghui on 2018/4/3.
 */
public class NtceCertifyDTO {
    private String category;
    private String certifyNo;
    private String deadline;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCertifyNo() {
        return certifyNo;
    }

    public void setCertifyNo(String certifyNo) {
        this.certifyNo = certifyNo;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
