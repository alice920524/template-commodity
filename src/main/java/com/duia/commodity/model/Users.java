package com.duia.commodity.model;

import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by liuhao on 2017/7/28.
 */
@Table(name = "users")
@Alias("Users")
public class Users implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String mobile;

    private String email;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "pic_url_mid")
    private String picUrlMid;

    @Column(name = "pic_url_min")
    private String picUrlMin;

    private Integer vip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrlMid() {
        return picUrlMid;
    }

    public void setPicUrlMid(String picUrlMid) {
        this.picUrlMid = picUrlMid;
    }

    public String getPicUrlMin() {
        return picUrlMin;
    }

    public void setPicUrlMin(String picUrlMin) {
        this.picUrlMin = picUrlMin;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", picUrlMid='" + picUrlMid + '\'' +
                ", picUrlMin='" + picUrlMin + '\'' +
                ", vip=" + vip +
                '}';
    }
}
