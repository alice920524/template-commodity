package com.duia.commodity.common.comp;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.ClassUpgrade;
import com.duia.security.param.UpgradeParam;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 升级对象
 * <p>
 * Created by 李国勇 on 2018/9/1.
 */
public class OrderUpgradeComp extends OrderAbComp {

    private final Object o;
    private final ClassType classType;
    private final ClassUpgrade upgrade;
    private final List<CommodityDTO> commodityDTOS;

    public OrderUpgradeComp(Object o, ClassUpgrade upgrade, ClassType classType) {
        this.o = o;
        this.upgrade = upgrade;
        this.classType = classType;
        this.commodityDTOS = this.init(upgrade, classType);
    }


    /**
     * 获取sku
     *
     * @return
     */
    public Integer getSku() {
        return classType.getSku();
    }

    /**
     * 获取课程类型
     *
     * @return
     */
    public Integer getCourseType() {
        return classType.getCourseType();
    }

    /**
     * 获取产品id
     *
     * @return
     */
    public Long getProductId() {
        return upgrade.getId();
    }

    /**
     * 获取产品名称
     *
     * @return
     */
    public String getProductName() {
        return classType.getTitle();
    }

    /**
     * 获取产品价格
     *
     * @return
     */
    public double getPrice() {
        return upgrade.getPrice();
    }
//
//    /**
//     * 获取邮寄地址标记
//     *
//     * @return
//     */
//    public Integer getAddressMark() {
//        return special.get
//    }

//    /**
//     * 获取图书价格
//     *
//     * @return
//     */
//    public double getBookPrice() {
//        throw new UnsupportedOperationException();
//    }

    /**
     * 获取老生优惠金额
     *
     * @return
     */
    public Integer getBenefitPrice() {
        return 0;
    }

    /**
     * 获取优惠券id
     *
     * @return
     */
    public String getDiscountId() {
        return ((UpgradeParam) o).getDiscountId();
    }

    /**
     * 获取特权id
     *
     * @return
     */
    public Long getVoucherDetailId() {
        return 0L;
    }

    /**
     * 获取商品列表集合
     *
     * @return
     */
    public List<CommodityDTO> commoditys() {
        return commodityDTOS;
    }

    /**
     * 构建商品集合
     *
     * @param upgrade
     * @param classType
     * @return
     */
    private List<CommodityDTO> init(ClassUpgrade upgrade, ClassType classType) {
        // 构造升级商品参数
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setId(upgrade.getId());
        commodityDTO.setName(classType.getTitle());
        commodityDTO.setAddressMark(upgrade.getAddressMark());
        commodityDTO.setComCostPrice(upgrade.getPrice());
        commodityDTO.setComRealPrice(upgrade.getPrice());
        // 多质保期，取一类信息
        commodityDTO.setGuaType(Objects.equals(classType.getGuaMul(), Constant.GUA_MUL_MULTITYPE)
                ? Constant.GUA_TYPE_FIRST : Constant.GUA_TYPE_COMMON);
        commodityDTO.setBuyType(OrderEnum.BUY_TYPE_COMMON.getKey());
        commodityDTO.setComMode(Constant.COM_MODE_NORMAL);
        commodityDTO.setCoverUrl(classType.getCoverUrl());
        commodityDTO.setType(6);
        commodityDTO.setCourseType(classType.getCourseType());
        commodityDTO.setBookTypeChecked(ClassTypeEnum.BOOK_TYPE_COMMON.getKey());
        commodityDTO.setBookPrice(upgrade.getStudyPackPrice());
        commodityDTO.setClassType(classType);
        return Arrays.asList(commodityDTO);
    }

}
