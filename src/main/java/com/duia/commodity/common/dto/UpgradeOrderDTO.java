package com.duia.commodity.common.dto;

import com.duia.commodity.model.AggrementTemplate;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.ClassUpgrade;

import java.util.List;

/**
 * Created by 李国勇 on 2018/6/12.
 */
public class UpgradeOrderDTO {

    /**
     * 升级信息
     */
    private ClassUpgrade upgrade;

    /**
     * 升级后班级信息
     */
    private ClassType classType;

    /**
     * 保险信息
     */
    private List<AggrementTemplate> insuranceList;


    public UpgradeOrderDTO(ClassUpgrade upgrade, ClassType classType, List<AggrementTemplate> insuranceList) {
        this.upgrade = upgrade;
        this.classType = classType;
        this.insuranceList = insuranceList;
    }

    public ClassUpgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(ClassUpgrade upgrade) {
        this.upgrade = upgrade;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public List<AggrementTemplate> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<AggrementTemplate> insuranceList) {
        this.insuranceList = insuranceList;
    }
}
