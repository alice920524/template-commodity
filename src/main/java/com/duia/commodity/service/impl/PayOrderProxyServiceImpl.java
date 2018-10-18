package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.common.comp.*;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.enums.CommodityEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.enums.Status;
import com.duia.enums.StudyPackage;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.*;
import com.duia.security.util.DESUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.duia.security.util.Constant.DES_KEY_DISCOUNT;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service
public abstract class PayOrderProxyServiceImpl implements PayOrderProxyService {

    @Autowired
    protected PayOrderService payOrderService;
    @Autowired
    private PayOrderMailingAddressService payOrderMailingAddressService;
    @Autowired
    protected SysDictService sysDictService;
    @Autowired
    protected CommodityService commodityService;
    @Autowired
    private CommoditySpecialService commoditySpecialService;
    @Autowired
    protected PayDiscountDetailService payDiscountDetailService;
    @Autowired
    protected CommodityExtraBuyingRelationService commodityExtraBuyingRelationService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private ClassTypeService classTypeService;
    @Autowired
    private ClassUpgradeService classUpgradeService;
    @Autowired
    protected PayOrderDetailService payOrderDetailService;
    @Autowired
    private LiveTeacherService liveTeacherService;
    @Autowired
    private UsersDepGrpRelService usersDepGrpRelService;
    @Autowired
    protected VoucherDetailService voucherDetailService;

    /**
     * 构建订单
     *
     * @param payOrder
     * @param pi
     * @param op
     * @return
     */
    public PayOrder payOrder(PayOrder payOrder, PayOrderInfoParam pi, Object op) {

        // 订单扩展
        this.extra(payOrder, pi, op);

        // 构造订单、商品参数
        Object o = this.constructParams(payOrder, op, pi);

        // 生成订单，并保存邮寄信息
        this.saveOrder(payOrder);

        // 生成订单详情
        payOrderDetailService.savePayOrderDetail(payOrder, o);

        return payOrder;
    }

    /**
     * 添加扩展
     *
     * @param order
     * @param pi
     * @param op
     */
    abstract void extra(PayOrder order, PayOrderInfoParam pi, Object op);

    /**
     * 构造订单参数
     *
     * @param payOrder
     * @param op
     * @param pi
     * @return
     */
    private Object constructParams(PayOrder payOrder, Object op, PayOrderInfoParam pi) {
        // 组合模式构造订单参数
        OrderAbComp o = this.commodityParams(payOrder, op);
        // 设置订单商品信息
        this.setOrderCommodityInfo(payOrder, o);
        // 判断学员是否是老生、及老生优惠
        this.setUserBenefit(payOrder, o);
        // 课程金额(套餐(套餐价格)、组合(满减)、单品(分享购、普通、抢购)) - 不含加价购
        double coursePrice = o.getPrice();
        // 获取订单商品，不含加价购
        List<CommodityDTO> commodityDTOS = o.commoditys();
        // 查询加价购信息
        commodityDTOS.addAll(this.extraBuyCommodityParams(payOrder, op));
        // 学习包相关处理
        this.studyPackage(op, commodityDTOS, payOrder);
        // 订单原始价格 = 商品价格（不含学习包） + 保险价格 + 学习包价格 （商品 + 加价购）+ 加价购价格（不含学习包）
        this.setRealPayPrice(payOrder, commodityDTOS, coursePrice);
        // 计算订单支付价格、并进行价格校验
        this.verifyAndSetOrderCostPrice(payOrder, o, pi, coursePrice);
        return o;
    }

    /**
     * 设置订单商品信息
     *
     * @param payOrder
     * @param o
     */
    private void setOrderCommodityInfo(PayOrder payOrder, OrderAbComp o) {
        payOrder.setCourseType(o.getCourseType()); // 班型类型
        payOrder.setProductId(o.getProductId()); // 配置产品id
        payOrder.setProgramName(o.getProductName()); // 配置产品名称
        payOrder.setCategoryId(o.getSku()); // 配置sku信息
        payOrder.setCategoryName(sysDictService.findById(payOrder.getCategoryId().longValue()).getDicName());// 配置商品所属sku名称
    }

    /**
     * 设置学员老生优惠信息
     *
     * @param payOrder
     * @param o
     */
    private void setUserBenefit(PayOrder payOrder, OrderAbComp o) {
        if (this.isOldUser(payOrder, o)) {
            payOrder.setIsOldUser(Status.VALID.getState());
            payOrder.setBenefitPrice(o.getBenefitPrice());
        } else {
            payOrder.setIsOldUser(Status.INVALID.getState());
            payOrder.setBenefitPrice(0);
        }
    }

    /**
     * 构造商品对象
     *
     * @param order
     * @param o
     * @return
     */
    OrderAbComp commodityParams(PayOrder order, Object o) {
        // 促销活动
        CommodityPromotion cp = this.promotionOrder(order, o);
        // 处理订单商品信息
        if (o instanceof PayOrderCommodityParam) { // 非升级订单
            PayOrderCommodityParam payOrderCommodityParam = (PayOrderCommodityParam) o;
            if (CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(payOrderCommodityParam.getType())
                    || CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey().equals(payOrderCommodityParam.getType())) {
                /**
                 * 套餐(套餐价格)、组合(满减)
                 * */
                CommoditySpecialParam commoditySpecialParam = payOrderCommodityParam.getCommoditySpecial();
                // 查询套餐信息
                CommoditySpecial special = commoditySpecialService.findById(commoditySpecialParam.getId());
                ///////////////////////////////////////////////////////////////////////////////////////////
                // 套餐、组合查询
                List<CommodityDTO> commodityDTOList = this.commodityService.selectSpecialCommodityByParam(commoditySpecialParam.getId(), commoditySpecialParam.getComList());
                // 校验
                this.checkCreateOrderComId(commodityDTOList, commoditySpecialParam.getComList());
                return Objects.equals(CommodityEnum.COMMODITY_TYPE_COMBO.getKey(), payOrderCommodityParam.getType()) ?
                        new OrderComboComp(o, special, commodityDTOList) : new OrderComBinComp(o, special, commodityDTOList);
            } else {
                /**
                 * 单品(分享购、普通、抢购、零元购)
                 */
                // 单品查询
                List<CommodityDTO> commodityDTOList = this.singleCommodity(payOrderCommodityParam.getCommodity());
                // 校验
                this.checkCreateOrderComId(commodityDTOList, Arrays.asList(payOrderCommodityParam.getCommodity()));
                // 单品商品处理
                this.singleCommodityExtra(order, cp, commodityDTOList);
                ///////////////////////////////////////////////////////////////////////////////////////////
                return new OrderSingleComp(o, commodityDTOList);
            }
        } else if (o instanceof UpgradeParam) { // 升级订单
            UpgradeParam u = (UpgradeParam) o;
            // 查询升级信息
            ClassUpgrade classUpgrade = classUpgradeService.findById(u.getUpgradeId());
            // 升级前班级信息
            Classes beforeClass = classesService.findById(classUpgrade.getBeforeClassid());
            ClassType beforeClassType = classTypeService.findById(beforeClass.getClassTypeId());
            // 升级后班级信息
            Classes afterClass = classesService.findById(classUpgrade.getAfterClassid());
            ClassType afterClassType = classTypeService.findById(afterClass.getClassTypeId());
            ///////////////////////////////////////////////////////////////////////////////////////////
            afterClassType.setTitle(beforeClassType.getTitle() + " 升级到 " + afterClassType.getTitle());
            return new OrderUpgradeComp(o, classUpgrade, afterClassType);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 查询单品商品
     *
     * @param c
     * @return
     */
    List<CommodityDTO> singleCommodity(CommodityParam c) {
        return commodityService.selectCommodityByParam(Arrays.asList(c));
    }

    /**
     * 单品商品扩展处理
     *
     * @param order
     * @param commodityDTOList
     */
    void singleCommodityExtra(PayOrder order, CommodityPromotion cp, List<CommodityDTO> commodityDTOList) {
        // 什么都不做，等待子类覆写
    }

    /**
     * 校验、设置订单支付价格
     *
     * @param payOrder
     * @param o
     * @param pi
     * @param coursePrice
     */
    private void verifyAndSetOrderCostPrice(PayOrder payOrder, OrderAbComp o, PayOrderInfoParam pi, double coursePrice) {
        double discountPrice = 0d;
        // 检查优惠券信息:普通券 - 添加扩展处理零元购
        PayDiscountDetail discountDetail = this.findPayDiscountDetail(payOrder, o);
        if (discountDetail != null) {
            discountPrice = Double.parseDouble(discountDetail.getDiscount());
        }
        // 订单实付价格 = 原始价格 - 老生优惠 - 优惠券价格（特定优惠券直接免单）
        this.setCostPrice(payOrder, o, coursePrice, discountPrice);
        // 校验商品价格区间
        this.verifyOrderCostPrice(payOrder, pi, discountDetail, coursePrice);
    }

    /**
     * 设置商品实付价格
     *
     * @param order         订单
     * @param o             订单商品
     * @param coursePrice   课程价格
     * @param discountPrice 优惠码价格
     */
    void setCostPrice(PayOrder order, OrderAbComp o, double coursePrice, double discountPrice) {
        double realpayPrice = order.getRealpayPrice();
        // 检查优惠券信息:
        double voucherPrice = this.findVoucherPrice(order, o);
        // 优惠后的价格：课程价格 - 老生优惠 - 优惠码价格 - 特权券价格
        double price = coursePrice - order.getBenefitPrice() - discountPrice - voucherPrice;
        // 不足一元，按照一元处理
        if (price < 1.00) {
            order.setCostPrice(new BigDecimal(realpayPrice - coursePrice + 1).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        } else {
            order.setCostPrice(new BigDecimal(realpayPrice - coursePrice + price).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
    }

    /**
     * 校验订单价格区间
     *
     * @param payOrder
     * @param pi
     * @param discountDetail
     * @param coursePrice
     */
    abstract void verifyOrderCostPrice(PayOrder payOrder, PayOrderInfoParam pi, PayDiscountDetail discountDetail, Double coursePrice);

    /**
     * 设置商品原始价格
     *
     * @param payOrder      订单
     * @param commodityDTOS 商品集合 - 包含加价购
     * @param coursePrice   课程价格
     * @return
     */
    private void setRealPayPrice(PayOrder payOrder, List<CommodityDTO> commodityDTOS, double coursePrice) {
        double studyPackagePrice = payOrder.getStudyPackagePrice();
        BigDecimal addMoneyPrice = new BigDecimal(0); // 加价购商品初始金额
        BigDecimal insurancePrice = new BigDecimal(0); // 保险初始金额
        for (CommodityDTO commodityDTO : commodityDTOS) {
            if (Objects.equals(OrderEnum.BUY_TYPE_ADD_MONEY.getKey(), commodityDTO.getBuyType())) { // 加价购金额
                addMoneyPrice = addMoneyPrice.add(new BigDecimal(commodityDTO.getComCostPrice()));
            }
            if (commodityDTO.getAggrementTemplate() != null) { // 保险金额
                insurancePrice = insurancePrice.add(new BigDecimal(commodityDTO.getAggrementTemplate().getExpense()));
            }
        }
        // 赋值订单原始价格
        payOrder.setRealpayPrice(new BigDecimal(coursePrice).add(insurancePrice).add(new BigDecimal(studyPackagePrice)).add(addMoneyPrice).doubleValue());
    }

    /**
     * 订单促销活动
     *
     * @param order
     * @param o
     * @return
     */
    abstract CommodityPromotion promotionOrder(PayOrder order, Object o);

    /**
     * 是否标记老生
     *
     * @param order
     * @return
     */
    abstract boolean isOldUser(PayOrder order, Object o);

    /**
     * 查询订单优惠券金额
     *
     * @param order
     * @param o
     * @return
     */
    PayDiscountDetail findPayDiscountDetail(PayOrder order, Object o) {
        OrderAbComp abComp = (OrderAbComp) o;
        if (StringUtils.isNotBlank(abComp.getDiscountId())) { //有优惠券
            PayDiscountDetail discountDetail = payDiscountDetailService.findById(convertDiscountIdToLong(abComp.getDiscountId()));
            order.setPayDiscountDetailId(discountDetail.getId());
            if (Constant.DISCOUNT_TYPE_YHTQ.equals(discountDetail.getDiscountType())) {
                order.setVoucherOrder(OrderEnum.VOUCHER_ORDER_YES.getKey());  // 特权订单
            }
            this.payDiscountPriceExtra(order, o, discountDetail);
            return discountDetail;
        }
        return null;
    }


    /**
     * 优惠券扩展处理
     *
     * @param order
     * @param o
     * @param discountDetail
     */
    void payDiscountPriceExtra(PayOrder order, Object o, PayDiscountDetail discountDetail) {
        // 什么都不做，等待子类覆写
    }

    /**
     * 查询特权券金额
     *
     * @param order
     * @param o
     * @return
     */
    abstract double findVoucherPrice(PayOrder order, Object o);

    /**
     * 查询加价购信息
     *
     * @param order
     * @param o
     * @return
     */
    protected List<CommodityDTO> extraBuyCommodityParams(PayOrder order, Object o) {
        List<CommodityDTO> commodityDTOList = new ArrayList<>();

        if (o instanceof PayOrderCommodityParam) {
            PayOrderCommodityParam param = (PayOrderCommodityParam) o;

            List<AddMoneyCommodity> addMoneyCommodityList = param.getAddMoneyCommodity();

            if (addMoneyCommodityList != null && !addMoneyCommodityList.isEmpty()) {

                for (AddMoneyCommodity addMoneyCommodity : addMoneyCommodityList) {
                    CommodityDTO dto = null;
                    if (CommodityEnum.COMMODITY_TYPE_SINGLE.getKey().equals(param.getType())) {//单品

                        dto = this.commodityExtraBuyingRelationService.selectCommodity(addMoneyCommodity.getExtraRId(), CommodityEnum.COMMODITY_TYPE_SINGLE.getKey(), addMoneyCommodity.getComId());
                    } else if (CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(param.getType())) {//套餐

                        dto = this.commodityExtraBuyingRelationService.selectCommodity(addMoneyCommodity.getExtraRId(), CommodityEnum.COMMODITY_TYPE_COMBO.getKey(), addMoneyCommodity.getComId());
                    } else if (CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey().equals(param.getType())) {//组合

                        dto = this.commodityExtraBuyingRelationService.selectCommodity(addMoneyCommodity.getExtraRId(), CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey(), addMoneyCommodity.getComId());
                    }

                    if (dto == null) {
                        throw new CheckPayOrderParamException("加价购数据被篡改>>>加价购类型>>>" + param.getType() + ">>>relationId>>>" + addMoneyCommodity.getExtraRId() + "商品ID>>>" + addMoneyCommodity.getComId());
                    }
                    dto.setBookTypeChecked(addMoneyCommodity.getBookTypeChecked());
                    commodityDTOList.add(dto);
                }
            }
        }

        return commodityDTOList;
    }

    /**
     * 保存订单后自定义操作
     *
     * @param order
     */
    abstract void _saveOrder(PayOrder order);

    /**
     * 保存订单
     */
    protected void saveOrder(PayOrder order) {

        // 保存订单
        payOrderService.save(order);

        // 保存订单后自定义操作
        _saveOrder(order);

        // 填写地址信息
        if (OrderEnum.MAIL.getKey().equals(order.getIsMail())) {
            payOrderMailingAddressService.savePayOrderMailAddress(true, order);
        }
    }

    /**
     * 学习包相关处理
     *
     * @param op            参数对象
     * @param commodityDTOS 商品集合
     * @param payOrder      订单
     */
    private void studyPackage(Object op, List<CommodityDTO> commodityDTOS, PayOrder payOrder) {
        // 学员未选择学习包
        if (!this.isStudentSelectStudyPack(op)) {
            // 标记订单学习包邮寄状态
            payOrder.setStudyPackagePrice(0d);
            // 处理学员不需要时，商品上的学习包状态
            for (CommodityDTO commodityDTO : commodityDTOS) {
                if (Objects.equals(commodityDTO.getAddressMark(), StudyPackage.VALID.getState())) {
                    commodityDTO.setBookPrice(0.0);
                    commodityDTO.setAddressMark(StudyPackage.INVALID.getState());
                    payOrder.setIsMail(OrderEnum.MAIL_STUDENT_NO.getKey());
                }
            }
        } else { // 处理学习包状态
            BigDecimal studyPackagePrice = new BigDecimal(0d);
            for (CommodityDTO commodityDTO : commodityDTOS) {
                if (Objects.equals(commodityDTO.getAddressMark(), StudyPackage.VALID.getState())) {
                    payOrder.setIsMail(OrderEnum.MAIL.getKey()); // 学员需要邮寄
                    studyPackagePrice = studyPackagePrice.add(new BigDecimal(commodityDTO.getBookPrice()));
                }
            }
            payOrder.setStudyPackagePrice(studyPackagePrice.doubleValue());
        }
    }

    /**
     * 校验下单商品ID是否正确
     *
     * @param commodityDTOList
     * @param params
     */
    protected void checkCreateOrderComId(List<CommodityDTO> commodityDTOList, List<CommodityParam> params) {
        if (null == commodityDTOList) {
            throw new CheckPayOrderParamException("数据被篡改>>>下单请求参数>>>" + JSON.toJSONString(params));
        } else if (commodityDTOList.size() != params.size()) {
            throw new CheckPayOrderParamException("数据被篡改>>>下单请求参数>>>" + JSON.toJSONString(params));
        }
    }

    /**
     * 学员是否选择学习包
     * <p>
     * 前台默认选择，添加学员不允许，余额可选
     *
     * @return
     */
    boolean isStudentSelectStudyPack(Object o) {
        return true;
    }

    /**
     * set老师信息
     */
    protected void setTeacherInfo(PayOrder payOrder, LiveTeacher liveTeacher) {
        if (null == liveTeacher) {
            return;
        }
        Long liveTeacherId = liveTeacher.getId();
        payOrder.setTeacherid(liveTeacherId);
        payOrder.setTeachername(liveTeacher.getTeachName());

        Long authUserId = liveTeacher.getAuthorityUserId();
        if (null == authUserId) {
            payOrder.setAuthorityUserId(this.liveTeacherService.findById(liveTeacherId).getAuthorityUserId());
        } else {
            payOrder.setAuthorityUserId(authUserId);
        }


    }

    /**
     * 获取默认对啊网老师
     */
    protected LiveTeacher getDefaultLiveTeacher() {
        return liveTeacherService.getLiveTeacherByUserEmail("duia@duia.com");
    }


    /**
     * 转换优惠券ID(包含解密)
     */
    protected Long convertDiscountIdToLong(String discountId) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(discountId)) {
            return null;
        }
        return Long.valueOf(new DESUtil().strDec(discountId, DES_KEY_DISCOUNT));
    }

    /**
     * 查询优惠券老师信息
     */
    protected LiveTeacher getDiscountLiveTeacher(String discountId) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(discountId)) {
            return null;
        }
        PayDiscountDetail discountDetail = payDiscountDetailService.findById(convertDiscountIdToLong(discountId));
        // 通过teacherId 查询teacherName
        return this.liveTeacherService.findById(discountDetail.getTeacherId());
    }

    /**
     * 获取升级之前班级的老师信息
     */
    protected LiveTeacher getUpgradeBeforeTeacher(Long upgradeId) {
        // 查询升级信息
        ClassUpgrade classUpgrade = classUpgradeService.findById(upgradeId);
        if (null == classUpgrade) {
            return null;
        }
        // 升级前班级信息
        Classes beforeClass = classesService.findById(classUpgrade.getBeforeClassid());

        // 有分组
        if (this.usersDepGrpRelService.isGroup(beforeClass.getTeacherId())) {
            return this.liveTeacherService.findById(beforeClass.getTeacherId());

        }
        return null;
    }

}
