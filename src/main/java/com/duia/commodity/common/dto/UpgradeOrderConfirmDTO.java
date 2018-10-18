package com.duia.commodity.common.dto;

import java.util.List;

public class UpgradeOrderConfirmDTO {

    private Integer sku;
    private Long upgradeId;// 升级ID
    private Long comId;// 商品ID
    private boolean showDiscount;// 是否显示优惠券
    private String title;// 课程名称
    private String webImg;// web封面图
    private String appImg;// App封面图
    private boolean showStudyPack;// 是否显示学习包
    private Double studyPackagePrice;// 学习包价格
    private Double costPrice;// 商品价格
    private Long classTypeId;// 班型ID
    private List<Integer> agreements;// 协议 icon

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Long getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Long upgradeId) {
        this.upgradeId = upgradeId;
    }

    public boolean isShowDiscount() {
        return showDiscount;
    }

    public void setShowDiscount(boolean showDiscount) {
        this.showDiscount = showDiscount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebImg() {
        return webImg;
    }

    public void setWebImg(String webImg) {
        this.webImg = webImg;
    }

    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    public boolean isShowStudyPack() {
        return showStudyPack;
    }

    public void setShowStudyPack(boolean showStudyPack) {
        this.showStudyPack = showStudyPack;
    }

    public Double getStudyPackagePrice() {
        return studyPackagePrice;
    }

    public void setStudyPackagePrice(Double studyPackagePrice) {
        this.studyPackagePrice = studyPackagePrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    public List<Integer> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<Integer> agreements) {
        this.agreements = agreements;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

}
