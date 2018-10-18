package com.duia.commodity.common.dto;

import java.io.Serializable;

/**
 * Created by 李国勇 on 2018/3/14.
 */
public class ServiceKeyDto implements Serializable {

    // 学员姓名
    private String name;
    // 手机号
    private String mobile;
    // 班级编号
    private String classNo;
    // 班型名称
    private String title;
    // 是否启用客服[0无1有]
    private Integer hasService;
    // 客服key
    private String serviceKey;
    // 智齿客服类型[0:个人,1:小组（默认）]
    private Integer keyType;
    // 机器人Id
    private String robotId;
    // 接待模式[1:仅机器人2:仅人工3:优先机器人4:优先人工]
    private Integer receptionMode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHasService() {
        return hasService;
    }

    public void setHasService(Integer hasService) {
        this.hasService = hasService;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public Integer getReceptionMode() {
        return receptionMode;
    }

    public void setReceptionMode(Integer receptionMode) {
        this.receptionMode = receptionMode;
    }
}
