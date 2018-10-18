package com.duia.commodity.dto;

import com.duia.commodity.model.CrmSaleHandoverNew;
import org.apache.ibatis.type.Alias;

/**
 * Created by 穆尚伟 on 2018/1/18.
 */
@SuppressWarnings("serial")
@Alias("CrmSaleHandoverNewDto")
public class CrmSaleHandoverNewDto extends CrmSaleHandoverNew{
    private String buyerNick;//买家昵称
    private String receiverName; //收货人姓名
    private String receiverState; //收货人省份
    private String receiverCity;//收货人城市
    private String receiverDistrict;//收货人地区
    private String receiverAddress;//详细地址
    private String receiverMobile;//收货人手机
    private String receiverPhone;//收货人电话

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
