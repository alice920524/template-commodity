package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "commodity_product")
public class CommodityProduct implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "com_id")
    private Long comId;

    @Column(name = "pro_id")
    private Long proId;

    /**
     * 1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     */
    @Column(name = "pro_type")
    private Integer proType;

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
     * @return com_id
     */
    public Long getComId() {
        return comId;
    }

    /**
     * @param comId
     */
    public void setComId(Long comId) {
        this.comId = comId;
    }

    /**
     * @return pro_id
     */
    public Long getProId() {
        return proId;
    }

    /**
     * @param proId
     */
    public void setProId(Long proId) {
        this.proId = proId;
    }

    /**
     * 获取1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     *
     * @return pro_type - 1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     */
    public Integer getProType() {
        return proType;
    }

    /**
     * 设置1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     *
     * @param proType 1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     */
    public void setProType(Integer proType) {
        this.proType = proType;
    }
}