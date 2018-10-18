package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.DiscountDTO;
import com.duia.commodity.common.dto.DiscountResultDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.common.enums.ErrorResultCode;
import com.duia.commodity.common.util.DESUtil;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.PayDiscountDetailMapper;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.enums.Status;
import com.duia.resultG.Result;
import com.duia.resultG.ResultCode;
import com.duia.resultG.ResultGenerator;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

import static com.duia.commodity.common.Constant.DES_KEY_DISCOUNT;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
@Transactional
public class PayDiscountDetailServiceImpl extends AbstractService<PayDiscountDetail> implements PayDiscountDetailService {
    @Resource
    private PayDiscountDetailMapper payDiscountDetailMapper;
    @Resource
    private CommodityService commodityService;
    @Resource
    private ClassTypeService classTypeService;
    @Resource
    private CommoditySpecialService commoditySpecialService;
    @Resource
    private VoucherDetailService voucherDetailService;
    @Resource
    private ClassUpgradeService classUpgradeService;
    @Resource
    private CommodityProductService commodityProductService;


    /**
     * 通过优惠券号码查询优惠券信息
     * @param discountCode
     * @return
     */
    @Override
    public PayDiscountDetail findCanUsedDiscountByDiscountCode(String discountCode)  {
        Condition condition = new Condition(PayDiscountDetail.class);
        try {
            condition.createCriteria()
                    .andEqualTo("discountCode", discountCode)
                    .andEqualTo("status", 0)
                    .andGreaterThanOrEqualTo("endDate", DateUtils.parseDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<PayDiscountDetail> discountDetailList = payDiscountDetailMapper.selectByCondition(condition);
        if (!discountDetailList.isEmpty())
            return discountDetailList.get(0);
        return null;
    }

    /**
     * 优惠券列表
     */
    @Override
    public Result checkDiscount(Users users, String discountCode, Long comId, Long specialId, Long upgradeId) {
        PayDiscountDetail discount = this.findCanUsedDiscountByDiscountCode(discountCode);
        if (discount == null) {
            return ResultGenerator.genFailResult("优惠码输入错误");
        }

        if (null != upgradeId) {
            // 升级
            if (Constant.DISCOUNT_TYPE_SJ.equals(discount.getDiscountType())) {
                return this.checkDiscountSJ(discount, upgradeId);
            }
        } else {
            // 普通-券(系统班、专题课、通用)、码
            if (Constant.DISCOUNT_TYPE_PT.equals(discount.getDiscountType())) {
                return this.checkDiscountPT(users, discount, comId, specialId);
                // 零元购-码
            } else if(Constant.DISCOUNT_TYPE_ZERO.equals(discount.getDiscountType())) {
                return this.checkDiscountZERO(discount, comId, specialId);
                // 专题课-码
            } else if (Constant.DISCOUNT_TYPE_THEMATIC.equals(discount.getDiscountType())) {
                return this.checkDiscountTHEMATIC(discount, comId, specialId);
                // 优惠特权-码
            } else if(Constant.DISCOUNT_TYPE_YHTQ.equals(discount.getDiscountType())) {
                return this.checkDiscountYHTQ(users, discount, comId, specialId);
            }
        }

        return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
    }

    @Override
    public DiscountResultDTO findDiscountList(Long userId, Integer appType, Long comId, Long specialId, Integer status, Integer page, Integer size) {
        // 优惠券类型
        Integer hasLimit;
        // 优惠券使用上限
        Double limitAmount;
        Integer skuId;
        Integer voucherAble;
        Commodity commodity;
        if (null != specialId && specialId > 0) {
            // 套餐组合取skuid、优惠券信息
            CommoditySpecial commoditySpecial = this.commoditySpecialService.findById(specialId);
            commodity = this.commodityService.findById(comId);
            skuId = commoditySpecial.getSkuId();
            voucherAble = commoditySpecial.getVoucherAble();
            hasLimit = commoditySpecial.getHasLimit();
            if (null == commoditySpecial.getLimitAmount()) {
                limitAmount = 0.0;
            } else {
                limitAmount = commoditySpecial.getLimitAmount().doubleValue();
            }
        } else if (null != comId && (null == specialId || 0 == specialId)) {
            // 单品取skuid、班型取优惠券信息
            commodity = this.commodityService.findById(comId);
            skuId = commodity.getSkuId();
            ClassType classType = this.classTypeService.findById(commodity.getClassTypeId());
            hasLimit = classType.getHasLimit();
            if (null == classType.getLimitAmount()) {
                limitAmount = 0.0;
            } else {
                limitAmount = classType.getLimitAmount().doubleValue();
            }
            voucherAble = classType.getVoucherAble();
        } else {
            return new DiscountResultDTO();
        }

        // 所有可用的优惠券(按照创建时间倒序排列)
        List<DiscountDTO> discountDTOS = this.payDiscountDetailMapper.selectDiscountList(appType, userId);
        // 特权(创建时间倒序排列)
        List<DiscountDTO> yhtqDiscountDTOS =this.voucherDetailService.selectYHTQDiscountList(appType, userId);

        // 总量=普通券+优惠特权
        int total = discountDTOS.size() + yhtqDiscountDTOS.size();
        int availableNum = 0;
        int unAvailableNum = 0;

        // 过滤出的券进行最后排序(可使用-最大金额排最前。不可使用-普通券-特权券分开)
        // 过滤出可用的券,并分页
        if (Integer.valueOf(1).equals(status)) {
            discountDTOS = this.availableDiscount(discountDTOS, hasLimit, limitAmount, skuId, commodity, specialId, voucherAble);
            availableNum = discountDTOS.size();
            unAvailableNum = total - availableNum;
        // 过滤出不可用的券
        } else if (Integer.valueOf(2).equals(status)) {
            discountDTOS = this.unAvailableDiscount(discountDTOS, hasLimit, limitAmount, skuId, commodity, specialId, voucherAble);
            // 不可用普通券 + 优惠特权(在不可用列表)
            unAvailableNum = discountDTOS.size() + yhtqDiscountDTOS.size();
            availableNum = total - unAvailableNum;
            // 聚合并分页
            discountDTOS.addAll(yhtqDiscountDTOS);
        } else {
            return new DiscountResultDTO();
        }

        // 分页
        discountDTOS = this.pageList(discountDTOS, page, size);

        for (DiscountDTO discountDTO : discountDTOS) {
            // 特权券
            if (Constant.DISCOUNT_TYPE_YHTQ.equals(discountDTO.getType())) {
                discountDTO.setUnAvailableType(ErrorResultCode.DISCOUNT_CASE1.code);
            }

            Long discountComId = discountDTO.getComId();
            if (null != discountComId) {
                discountDTO.setComName(this.commodityService.findById(discountComId).getName());
            }

            // id 加密
            if (null != discountDTO.getDiscountDetailId()) {
                discountDTO.setDiscountDetailId(new DESUtil().strEnc(discountDTO.getDiscountDetailId(), DES_KEY_DISCOUNT));
            }
        }

        return new DiscountResultDTO(discountDTOS, availableNum, unAvailableNum);
    }

    /**
     * 可用的券
     */
    private List<DiscountDTO> availableDiscount(List<DiscountDTO> discountDTOS, Integer hasLimit, Double limitAmount, Integer skuId, Commodity commodity, Long specialId, Integer voucherAble) {

        // 可用券集合
        List<DiscountDTO> availableDiscount = new ArrayList<>();
        Double maxPrice = 0.0;
        for (int i = 0; i < discountDTOS.size(); i++) {
            DiscountDTO discountDTO = discountDTOS.get(i);

            int code = isAvailable(voucherAble, hasLimit, limitAmount, commodity, specialId, skuId, discountDTO);
            if (code == ResultCode.SUCCESS.SUCCESS.code) {

                // 统计最高价格
                if (maxPrice < discountDTO.getPrice())
                    maxPrice = discountDTO.getPrice();

                availableDiscount.add(discountDTO);
            }
        }

        // 排序

        // 最大价格-普通
        List<DiscountDTO> maxPriceDiscount = new ArrayList<>();
        // 其他普通券
        List<DiscountDTO> otherPriceDiscount = new ArrayList<>();

        for (int i = 0; i < availableDiscount.size(); i++) {

            DiscountDTO discountDTO = availableDiscount.get(i);
            if (maxPrice.equals(discountDTO.getPrice())) {
                maxPriceDiscount.add(discountDTO);
            } else {
                otherPriceDiscount.add(discountDTO);
            }
        }

        maxPriceDiscount.addAll(otherPriceDiscount);

        return maxPriceDiscount;
    }

    /**
     * 不可用的券
     */
    private List<DiscountDTO> unAvailableDiscount(List<DiscountDTO> discountDTOS, Integer hasLimit, Double limitAmount, Integer skuId, Commodity commodity, Long specialId, Integer voucherAble) {


        // 不可用集合-普通
        List<DiscountDTO> unAvailableDiscountPT = new ArrayList<>();
        // 不可用集合-优惠特权
        List<DiscountDTO> unAvailableDiscountYHTQ = new ArrayList<>();

        for (int i = 0; i < discountDTOS.size(); i++) {
            DiscountDTO discountDTO = discountDTOS.get(i);

            int code = isAvailable(voucherAble, hasLimit, limitAmount, commodity, specialId, skuId, discountDTO);
            if (code == ResultCode.SUCCESS.code) {
                continue;
            }
            //set 不可用类型
            discountDTO.setUnAvailableType(code);

            if (Constant.DISCOUNT_TYPE_YHTQ.equals(discountDTO.getType())) {
                unAvailableDiscountYHTQ.add(discountDTO);
            }
            if (Constant.DISCOUNT_TYPE_PT.equals(discountDTO.getType())) {
                unAvailableDiscountPT.add(discountDTO);
            }
        }

        // 数据合并 排序 普通在前、特权在后
        unAvailableDiscountPT.addAll(unAvailableDiscountYHTQ);

        return unAvailableDiscountPT;
    }

    // 分页
    private List<DiscountDTO> pageList(List<DiscountDTO> discountDTOList, Integer page, Integer size) {
        if (page < 1) {
            page = 1;
        }
        int listSize = discountDTOList.size();

        int fromIndex = (page - 1) * size;

        if (fromIndex > listSize) {
            fromIndex = listSize;
        }

        int toIndex = (fromIndex + size) > listSize ? listSize : (fromIndex + size);

        return discountDTOList.subList(fromIndex, toIndex);
    }

    /**
     * 是否可用
     * <p>
     * voucherAble:券开关
     * hasLimit:无上限、又上限、零元购
     * limitAmount:上限金额
     * comId:单品商品ID、套餐组合为null
     * skuId:单品skuId、套餐组合skuId
     * commodity:主商品的商品信息
     */
    private int isAvailable(Integer voucherAble, Integer hasLimit, Double limitAmount, Commodity commodity, Long specialId, Integer skuId, DiscountDTO discountDTO) {

        //特权券
        //--------------------------------------------------------------------------------------------------------------
        // 特权券不能直接使用(通过码的方式用)
        if (Constant.DISCOUNT_TYPE_YHTQ.equals(discountDTO.getType())) {
            return ErrorResultCode.DISCOUNT_CASE1.code;
        }
        //--------------------------------------------------------------------------------------------------------------

        // 班型 或者 套餐组合 是否支持用券
        //--------------------------------------------------------------------------------------------------------------
        // 商品使用券开关没开启
        if (Status.INVALID.getState() == voucherAble) {
            return ErrorResultCode.DISCOUNT_CASE2.code;
        }
        //--------------------------------------------------------------------------------------------------------------


        // sku不相同
        if (!discountDTO.getSkuId().equals(skuId)) {
            return ErrorResultCode.DISCOUNT_CASE3.code;
        }

        // 商品ID不为空匹配
        Long discountComId = discountDTO.getComId();

        // 商品ID和参数传递的商品ID不符合
        if (null != discountComId && !discountComId.equals(commodity.getId())) {
            return ErrorResultCode.DISCOUNT_CASE3.code;
        }

        // 优惠券判断
        //--------------------------------------------------------------------------------------------------------------
        // 零元购(不允许用)
        if (Constant.DISCOUNT_USE_ZERO_PAY.equals(hasLimit)) {
            return ErrorResultCode.DISCOUNT_CASE5.code;
        }
        //--------------------------------------------------------------------------------------------------------------

        // 班型比较
        //--------------------------------------------------------------------------------------------------------------
        if (null != discountDTO.getCourseType()) {

            // -1 通用券(系统班(单品、套餐、组合、分享购)、专题课 可以用)
            if (discountDTO.getCourseType().equals(-1)) {
                return ResultCode.SUCCESS.code;
            }
            // 系统班、专题课
            if (!Objects.equals(commodity.getCourseType(), discountDTO.getCourseType())) {
                return ErrorResultCode.DISCOUNT_CASE7.code;
            }

        }

        //--------------------------------------------------------------------------------------------------------------
        return ResultCode.SUCCESS.code;
    }
    /**
     * 升级
     * */
    private Result checkDiscountSJ(PayDiscountDetail discount, Long upgradeId) {
        ClassUpgrade classUpgrade = classUpgradeService.findById(upgradeId);

        CommodityProduct commodityProduct = commodityProductService.findBy(classUpgrade.getAfterClassid(), 6);

        if (commodityProduct == null) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        Commodity commodity = this.commodityService.findById(commodityProduct.getComId());
        // 通用校验(sku、comId)
        Result result = this.checkDiscountCommonFilds(discount, commodity);

        if (result.getCode() !=com.duia.resultG.ResultCode.SUCCESS.code) {
            return result;
        }
        Double discountPrice = Double.valueOf(discount.getDiscount());
        ClassType classType = classTypeService.findById(commodity.getClassTypeId());

        // 开关关闭
        if (this.isCloseSwitch(classType.getCoupon())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        // 配置上限,并且达到上限
        if (this.isTopLimit(classUpgrade.getHasLimit(), classUpgrade.getLimitAmount(), discountPrice)) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), discount.getDiscount(), 0));
    }

    /**
     * 优惠特权校验
     * */
    private Result checkDiscountYHTQ(Users users, PayDiscountDetail discount, Long comId, Long specialId) {
        // user 判断
        if (!Objects.equals(users.getId(), discount.getUserId())) {
            return ResultGenerator.genFailResult("该优惠券只限本人使用");
        }

        Commodity commodity = commodityService.findById(comId);

        // 通用校验(sku、comId)
        Result result = this.checkDiscountCommonFilds(discount, commodity);

        if (result.getCode() != com.duia.resultG.ResultCode.SUCCESS.code) {
            return result;
        }

        // 非系统班
        if (!isSystemClasses(commodity.getCourseType())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        Double discountPrice = Double.valueOf(discount.getDiscount());

        if (this.isSpecial(specialId)) {
            CommoditySpecial commoditySpecial = commoditySpecialService.findById(specialId);

            // 开关关闭
            if (this.isCloseSwitch(commoditySpecial.getCoupon())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 设置上限并达到上限
            if (this.isTopLimit(commoditySpecial.getHasLimit(), commoditySpecial.getLimitAmount(), discountPrice)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), discount.getDiscount(), 0));
        } else {
            ClassType classType = classTypeService.findById(commodity.getClassTypeId());

            // 开关关闭
            if (this.isCloseSwitch(classType.getCoupon())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 设置上限并达到上限
            if (this.isTopLimit(classType.getHasLimit(), classType.getLimitAmount(), discountPrice)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 零元购不能用
            if (Objects.equals(classType.getHasLimit(), Constant.DISCOUNT_USE_ZERO_PAY)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), discount.getDiscount(), 0));
        }
    }

    /**
     * 专题课校验
     * */
    private Result checkDiscountTHEMATIC(PayDiscountDetail discount, Long comId, Long specialId) {
        Commodity commodity = commodityService.findById(comId);

        // 通用校验(sku、comId)
        Result result = this.checkDiscountCommonFilds(discount, commodity);

        if (result.getCode() != com.duia.resultG.ResultCode.SUCCESS.code) {
            return result;
        }

        // 非专题课
        if (!this.isSpecialClasses(commodity.getCourseType())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        // 套餐、组合不能用
        if (this.isSpecial(specialId)) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        ClassType classType = classTypeService.findById(commodity.getClassTypeId());

        // 开关关闭
        if (this.isCloseSwitch(classType.getCoupon())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), discount.getDiscount(), 0));
    }

    /**
     * 零元购
     * */
    private Result checkDiscountZERO(PayDiscountDetail discount, Long comId, Long specialId) {
        Commodity commodity = commodityService.findById(comId);

        // 通用校验(sku、comId)
        Result result = this.checkDiscountCommonFilds(discount, commodity);

        if (result.getCode() != com.duia.resultG.ResultCode.SUCCESS.code) {
            return result;
        }

        // 套餐、组合不能用
        if (this.isSpecial(specialId)) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        ClassType classType = classTypeService.findById(commodity.getClassTypeId());

        // 开关关闭
        if (this.isCloseSwitch(classType.getCoupon())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        // 非零元购
        if(!Objects.equals(classType.getHasLimit(), Constant.DISCOUNT_USE_ZERO_PAY)) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), "0", 1));
    }

    /**
     * 普通购买
     * */
    private Result checkDiscountPT(Users users, PayDiscountDetail discount, Long comId, Long specialId) {
        Commodity commodity = this.commodityService.findById(comId);
        // 通用校验(sku、comId)
        Result result = this.checkDiscountCommonFilds(discount, commodity);

        if (result.getCode() != com.duia.resultG.ResultCode.SUCCESS.code) {
            return result;
        }

        // 券
        if (Objects.equals(discount.getUserVisible(), 1)) {
            // user 判断
            if (!Objects.equals(users.getId(), discount.getUserId())) {
                return ResultGenerator.genFailResult("该优惠券只限本人使用");
            }

            return this.voucherCheck(commodity, discount, specialId);
            // 码
        } else {
            return this.commonCouponCheck(commodity, discount, specialId);
        }
    }
    /**
     * 普通券(系统班券、专题课券、通用券)
     * */
    private Result voucherCheck(Commodity commodity, PayDiscountDetail discount, Long specialId) {

        // 非通用券,校验班型类型
        if (!Constant.DISCOUNT_COURSE_TYPE_ALL.equals(discount.getCourseType())) {
            // courseType  -1:通用  0:系统班  1:专题课
            if (!Objects.equals(commodity.getCourseType(), discount.getCourseType())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }
        }

        // 套餐、组合
        if (this.isSpecial(specialId)) {

            // 专题课券不能用到套餐组合
            if (ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey().equals(discount.getCourseType())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            CommoditySpecial commoditySpecial = commoditySpecialService.findById(specialId);
            // 开关校验
            if (this.isCloseSwitch(commoditySpecial.getVoucherAble())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }
            // 单品
        } else {
            ClassType classType = classTypeService.findById(commodity.getClassTypeId());
            // 开关校验
            if (this.isCloseSwitch(classType.getVoucherAble())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 允许使用优惠券
            if (Objects.equals(classType.getHasLimit(), Constant.DISCOUNT_USE_ZERO_PAY)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }
        }

        return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), discount.getDiscount(), 0));
    }
    /**
     * 普通码
     * */
    private Result commonCouponCheck(Commodity commodity, PayDiscountDetail discount, Long specialId) {
        // 商品班型类型(系统班)
        if (!this.isSystemClasses(commodity.getCourseType())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        Double discountPrice = Double.valueOf(discount.getDiscount());

        // 套餐、组合
        if (this.isSpecial(specialId)) {
            CommoditySpecial commoditySpecial = commoditySpecialService.findById(specialId);

            // 开关关闭
            if (this.isCloseSwitch(commoditySpecial.getCoupon())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 设置上限并达到上限
            if (this.isTopLimit(commoditySpecial.getHasLimit(), commoditySpecial.getLimitAmount(), discountPrice)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }
            // 单品
        } else {
            ClassType classType = classTypeService.findById(commodity.getClassTypeId());

            // 开关关闭
            if (this.isCloseSwitch(classType.getCoupon())) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 设置上限并达到上限
            if (this.isTopLimit(classType.getHasLimit(), classType.getLimitAmount(), discountPrice)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }

            // 零元购不能用
            if (Objects.equals(classType.getHasLimit(), Constant.DISCOUNT_USE_ZERO_PAY)) {
                return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
            }
        }
        return ResultGenerator.genSuccessResult(this.buildDiscountResult(discount.getId(), discount.getDiscount(), 0));
    }

    /**
     * 是否是套餐组合
     * true:套餐、组合
     * false:单品
     * */
    private boolean isSpecial(Long specialId) {
        return (null != specialId && specialId > 0);
    }

    /**
     * 开关是否关闭(券、码 开关)
     * true:关闭
     * false:开启
     * */
    private boolean isCloseSwitch(Integer couponSwitch) {
        return Objects.equals(couponSwitch, Constant.DISCOUNT_USE_REFUSE);
    }

    /**
     * 是否达到使用上限
     * true:达到上限
     * false:未达到上限 或 没有开启上限
     * */
    private boolean isTopLimit(Integer hasLimit, Integer limitAmount, Double discountPrice) {
        return Objects.equals(hasLimit, Constant.DISCOUNT_USE_TOP_LIMIT) && limitAmount < discountPrice;
    }
    /**
     * 是否是系统班
     * true:系统班
     * false:非系统班
     * */
    private boolean isSystemClasses(Integer courseType) {
        return ClassTypeEnum.COURSE_TYPE_SYSTEM.getKey().equals(courseType);
    }
    /**
     * 是否是专题课
     * true:专题课
     * false:非专题课
     * */
    private boolean isSpecialClasses(Integer courseType) {
        return ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey().equals(courseType);
    }

    private Map buildDiscountResult(Long discountId, String discountPrice, Integer zeroPay) {
        Map<String, Object> data = Maps.newLinkedHashMap();
        //DES加密，防止恶意篡改
        data.put("id", new DESUtil().strEnc(discountId.toString(), DES_KEY_DISCOUNT));
        data.put("zeroPay", zeroPay);
        data.put("price", Double.valueOf(discountPrice));
        return data;
    }

    private String getDiscountErrorMsg(PayDiscountDetail discount) {
        // 券提示语(系统班券、专题课券、通用券)
        if (Integer.valueOf(1).equals(discount.getUserVisible()) && Constant.DISCOUNT_TYPE_PT.equals(discount.getDiscountType())) {
            return "该优惠券不适用于此课程";
        } else {
            return "该优惠码不适用于此课程";
        }
    }

    /**
     * 校验优惠券普通字段
     *  skuId:skuId
     *  comId:商品ID
     * */
    private Result checkDiscountCommonFilds(PayDiscountDetail discount, Commodity commodity) {
        // sku比较
        if (!Objects.equals(discount.getSkuId(), commodity.getSkuId())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        // 商品ID比较
        if (null != discount.getCommodId() && !Objects.equals(discount.getCommodId(), commodity.getId())) {
            return ResultGenerator.genFailResult(this.getDiscountErrorMsg(discount));
        }

        return ResultGenerator.genSuccessResult();
    }
}
