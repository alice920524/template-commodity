package com.duia.commodity.common.dto;

/**
 * Created by 李国勇 on 2018/6/25.
 */
public class BookOrderParamDTO {

    private Long comId; // 商品id
    private String discountId; // 加密优惠券
    private Integer promotion; // 促销类型
    private String source; // 来源
    private String remark; // 备注 os appt
    private Integer os;//平台[1:ios,2:android]


    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
