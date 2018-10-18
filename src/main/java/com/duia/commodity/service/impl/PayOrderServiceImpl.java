package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.*;
import com.duia.commodity.common.enums.*;
import com.duia.commodity.common.util.DateUtils;
import com.duia.commodity.common.util.DoubleUtil;
import com.duia.commodity.common.util.OrderPriceUtil;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.core.PayOrderAuthProxy;
import com.duia.commodity.dao.PayOrderMapper;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.enums.PayStatus;
import com.duia.enums.Sales;
import com.duia.enums.Status;
import com.duia.enums.StudyPackage;
import com.duia.resultG.ResultGenerator;
import com.duia.security.decrypt.Dec;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.*;
import com.duia.util.sort.SortUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

import static com.duia.commodity.common.Constant.BATCH_PAY_MAX_MONEY;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
@Transactional
public class PayOrderServiceImpl extends AbstractService<PayOrder> implements PayOrderService {

    private static Logger logger = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    @Resource
    private PayOrderMapper payOrderMapper;
    @Resource
    private PayOrderDetailService payOrderDetailService;
    @Resource
    private AggrementTemplateService aggrementTemplateService;
    @Resource
    private ClassTypeService classTypeService;
    @Resource
    private CommoditySpecialService commoditySpecialService;
    @Resource
    private CommodityPromotionService commodityPromotionService;
    @Resource
    private ClassUpgradeService classUpgradeService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private ClassesService classesService;
    @Resource
    private ClassStudentService classStudentService;
    @Resource
    private CommodityProductService commodityProductService;
    @Resource
    private PayOrderTransService transService;
    @Resource
    private PayOrderAuthProxy payOrderAuthProxy;
    @Resource
    private UserIosBalanceService userIosBalanceService;
    @Resource
    private TerminalDisplayService terminalDisplayService;

    @Override
    public ModelAndView handleMultiPayOrder(String payNum, String payType, Users user) {
        ModelAndView mv = new ModelAndView();
        PayOrder order = this.findBy("payNum", payNum);
        // 用户信息异常
        if (!Objects.equals(user.getId(), order.getUserId())) {
            mv.setViewName("payNoOrder");
            return mv;
        }

        // 配置子订单 及支付订单相关参数
        this.makeMvParams(mv, order, payType);

        mv.addObject("sku", order.getCategoryId());
        mv.addObject("costPrice", order.getCostPrice());
        return mv;
    }

    /**
     * 配置相关参数信息：子订单信息、已支付成功数、跳转视图
     *
     * @param mv
     * @param order
     * @param payType
     */
    private void makeMvParams(ModelAndView mv, PayOrder order, String payType) {

        // 查询订单的子订单信息
        List<PayOrder> childs = payOrderMapper.findChildsPayOrder(order.getId());

        // 已支付订单数量
        Integer paidCount = 0;
        if (CollectionUtils.isEmpty(childs)) { // 订单未拆分
            childs = this.handlerChildOrders(order, payType);
        } else { // 已拆分子订单
            for (int i = 0; i < childs.size(); i++) {
                if (this.vPaySuccess(childs.get(i), payType)) {
                    paidCount++;
                }
            }
        }

        // 设置已支付订单数
        mv.addObject("paidCount", paidCount);

        // 子订单排序
        SortUtils.sortTheList(childs, "orderBatch", SortUtils.SORT_ORDER_ASC);
        mv.addObject("childOrderList", childs);

        // 设置viewName
        if (Objects.equals(Constant.PAY_TYPE_UMONEY, payType)) {
            mv.setViewName(paidCount < 1 ? "payBd" : "payBdTwo");
        } else if (Objects.equals(Constant.PAY_TYPE_COFFEE, payType)) {
            mv.setViewName(paidCount < 1 ? "payCoffee" : "payCoffeeTwo");
        } else if (Objects.equals(Constant.PAY_TYPE_HAIMI, payType)) {
            mv.setViewName(paidCount < 1 ? "payHaiMi" : "payHaiMiTwo");
        } else if (Objects.equals(Constant.PAY_TYPE_BATCH, payType)) {
            mv.setViewName("payFp");
        }
    }

    /**
     * 生成子订单，并改变父订单类型
     *
     * @param order
     * @param payType
     * @return
     */
    private List<PayOrder> handlerChildOrders(PayOrder order, String payType) {

        // 商品总价格
        Double oPrice = order.getCostPrice();
        if (Objects.equals(Constant.PAY_TYPE_BATCH, payType)) { // 分批支付
            Double maxMoney = Double.parseDouble(BATCH_PAY_MAX_MONEY);
            int orderBatch = 0;// 子订单序号
            Double price;// 子订单价格
            do {
                orderBatch++;
                price = oPrice > maxMoney ? maxMoney : oPrice;
                oPrice = DoubleUtil.sub(oPrice, price);
                this.insertChildOrder(order, price, orderBatch, null);
            } while (oPrice > 0);
        } else { // 百度、咖啡支付
            Double percent = 1d;
            if (Objects.equals(Constant.PAY_TYPE_UMONEY, payType)) {
                percent = Constant.BAIDU_PAY_PERCENT;
            } else if (Objects.equals(Constant.PAY_TYPE_COFFEE, payType)) {
                percent = Constant.COFFEE_EASE_PAY_PERCENT;
            } else if (Objects.equals(Constant.PAY_TYPE_HAIMI, payType)) {
                percent = Constant.HAIMI_PAY_PERCENT;
            }
            Double oneMoney = DoubleUtil.percent(oPrice, percent);
            // 第一笔百度有钱花、咖啡易融
            this.insertChildOrder(order, oneMoney, 1, payType);
            // 第二笔尾款
            this.insertChildOrder(order, DoubleUtil.sub(oPrice, oneMoney), 2, null);
        }
        // 设置父订单支付类型
        order.setPayType(payType);
        order.setOrderType(OrderEnum.ORDER_TYPE_PARENT.getKey());
        payOrderMapper.updateByPrimaryKey(order);
        return payOrderMapper.findChildsPayOrder(order.getId());
    }

    /**
     * 保存子订单
     *
     * @param order
     * @param costPrice
     * @param orderBatch
     * @param payType
     * @return
     */
    private PayOrder insertChildOrder(PayOrder order, Double costPrice, Integer orderBatch, String payType) {
        PayOrder childOrder = this.generateChildOrder(order, costPrice);
        childOrder.setOrderParentId(order.getId());
        childOrder.setOrderBatch(orderBatch);
        childOrder.setPayType(payType);
        childOrder.setPayNum(this.getChildOrderNum(childOrder.getPayNum(), orderBatch));
        payOrderMapper.insert(childOrder);
        return childOrder;
    }

    /**
     * 检测订单是否已支付
     *
     * @param payOrder
     * @param payType
     * @return
     */
    private boolean vPaySuccess(PayOrder payOrder, String payType) {
        if (Objects.equals(Constant.PAY_TYPE_BATCH, payType)) {
            return Objects.equals(payOrder.getPayStatus(), PayStatus.PAY_STATUS_SUCCESS.getState());
        } else {
            return Objects.equals(payOrder.getPayStatus(), PayStatus.PAY_STATUS_SUCCESS.getState()) || Objects.equals(payOrder.getPayStatus(), PayStatus.PAY_STATUS_THROUGH.getState());
        }
    }

    /**
     * ios下单(暂定)
     */
    @Override
    public com.duia.resultG.Result createOrderIOS(HttpServletRequest request, Users user, String p) {
        String appType = request.getParameter("appType");
        // p参数解密
        PayOrderCommodityParam param = Dec.P(PayOrderCommodityParam.class, p);

        PayOrderParam orderParam = new PayOrderParam();
        if (null == appType) {
            orderParam.setSource(Constant.ORDER_SOURCE_IOS + "1");
        } else {
            orderParam.setSource(Constant.ORDER_SOURCE_IOS + appType);
        }
        orderParam.setC(param);

        PayOrderInfoParam payOrderInfoParam = new PayOrderInfoParam();
        payOrderInfoParam.setUserId(user.getId());
        orderParam.setP(payOrderInfoParam);
        PayOrder payOrder = payOrderAuthProxy.auth(request, orderParam);

        OrderCreateSuccessDTO orderCreateSuccessDTO = new OrderCreateSuccessDTO(payOrder);

        // 零元购下单 直接开班
        if (Constant.PAY_TYPE_ZEROPAY.equals(payOrder.getPayType())) {
            // auth中已经根据零元购标识进行开班
            // 需要的是根据订单号取班级ID
            // 事务问题,在controller获取班级的ID
            return ResultGenerator.genSuccessResult(orderCreateSuccessDTO);
        } else {
            // 非零元购 校验余额,余额不够下单成功,返回余额不足标识
            Double balance = this.userIosBalanceService.getUserIosBalance(user.getId());
            Double orderCostPrice = payOrder.getCostPrice();
            if (orderCostPrice > balance) {// 余额不足
                return ResultGenerator.genCustomResult(ErrorResultCode.BALANCE_ERROR.code, null, null);
            }

            return ResultGenerator.genSuccessResult(orderCreateSuccessDTO);
        }
    }

    /**
     * 查询订单
     */
    @Override
    public PayOrder findPayOrderByPayNum(String payNum) {
        if (null == payNum) {
            return null;
        }
        return this.findBy("payNum", payNum);
    }

    private CommodityResultDTO confirmSpecialOrder(HttpServletRequest request, PayOrderCommodityParam param, Users user, Integer appTyp) {
        CommoditySpecialParam specialParam = param.getCommoditySpecial();
        List<CommodityParam> params = specialParam.getComList();

        List<CommodityDTO> commodityDTOList = this.commodityService.selectSpecialCommodityByParam(specialParam.getId(), params);

        //校验商品ID是否正确 根据ID数量
        this.checkCreateOrderComId(commodityDTOList, params);

        CommodityResultDTO resultDTO = new CommodityResultDTO();

        //保险价格
        BigDecimal insurancePrice = new BigDecimal(0);
        // 学习包价格
        BigDecimal studyPackPrice = new BigDecimal(0);
        // 是否有学习包 默认没有
        Integer hasStudyPack = StudyPackage.INVALID.getState();

        // 套餐/组合商品集合
        List<CommodityBaseDTO> comList = new ArrayList<>(commodityDTOList.size());

        for (CommodityDTO commodityDTO : commodityDTOList) {
            CommodityBaseDTO commodityBaseDTO = this.orderConfirmCommodityBaseDto(commodityDTO);

            comList.add(commodityBaseDTO);

            insurancePrice = insurancePrice.add(new BigDecimal(commodityBaseDTO.getInsurancePrice()));
            studyPackPrice = studyPackPrice.add(new BigDecimal(commodityBaseDTO.getStudyPackPrice()));

            if (isMail(commodityDTO.getAddressMark())) {
                hasStudyPack = StudyPackage.VALID.getState();
            }
        }

        CommoditySpecialBaseDTO specialBaseDTO = new CommoditySpecialBaseDTO();
        // set 套餐/组合 商品集合
        specialBaseDTO.setComList(comList);


        // 查询组合信息
        CommoditySpecial special = commoditySpecialService.findById(specialParam.getId());
        if (CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(param.getType())) {// 套餐
            // TODO 价格计算方式 套餐价+保险价+学习包价
            specialBaseDTO.setCostPrice(new BigDecimal(special.getRealPrice()).add(insurancePrice).add(studyPackPrice).doubleValue());
            // TODO 取 costPrice值
            specialBaseDTO.setRealpayPrice(specialBaseDTO.getCostPrice());
        } else if (CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey().equals(param.getType())) { // 组合
            Double coursePrice = this.getCommodityTotalPrice(commodityDTOList);
            // TODO 价格计算方式 商品总价+保险价+学习包价
            specialBaseDTO.setRealpayPrice(new BigDecimal(coursePrice).add(insurancePrice).add(studyPackPrice).doubleValue());
            // TODO 商品总价+保险价+学习包价 - 满减
            specialBaseDTO.setCostPrice(new BigDecimal(specialBaseDTO.getRealpayPrice()).subtract(new BigDecimal(this.discountPrice(special, commodityDTOList))).doubleValue());
        }


        specialBaseDTO.setHasStudyPack(hasStudyPack);
        specialBaseDTO.setType(param.getType());
        specialBaseDTO.setId(special.getId());
        specialBaseDTO.setName(special.getName());

        /**
         * 调用是否可以使用优惠通用方法
         */
        this.discountSwitch(special.getCoupon(), special.getVoucherAble(), resultDTO);

        resultDTO.setCommoditySpecial(specialBaseDTO);
        resultDTO.setType(param.getType());
        resultDTO.setSku(special.getSkuId().longValue());

        this.confirmOldStudentSale(special.getBenefitMark(), special.getBenefitPrice(), user.getId(), resultDTO);// 老生优惠

        return resultDTO;
    }

    // 单品
    private CommodityResultDTO confirmCommodityOrder(HttpServletRequest request, PayOrderCommodityParam param, Users user, Integer appType) {
        List<CommodityParam> params = Arrays.asList(param.getCommodity());
        List<CommodityDTO> commodityDTOList = this.commodityService.selectCommodityByParam(params);
        /***********************************************校验***********************************************/
        //校验商品ID是否正确 根据ID数量
        this.checkCreateOrderComId(commodityDTOList, params);
        /***********************************************校验***********************************************/
        CommodityDTO commodityDTO = commodityDTOList.get(0);
        // commodityDTO -> CommodityBaseDTO
        CommodityBaseDTO commodityBaseDTO = this.orderConfirmCommodityBaseDto(commodityDTO);

        // 分享购信息
        CommodityPromotion commodityPromotion;
        if (param.getPromotionId() != null) {

            commodityPromotion = this.commodityPromotionService.findById(param.getPromotionId());

            if (this.checkCommodityPromotion(commodityPromotion)) {
                // 分享购(类型、终端、是否分享)
                if (CommodityPromotionEnum.TYPE_SHARE.getKey().equals(commodityPromotion.getType())
                        && this.terminalDisplayService.terminalCheck(commodityPromotion.getId(), commodityPromotion.getType(), appType)
                        && this.commodityPromotionService.isShareCommodity(commodityDTO.getId(), user.getId())) {

                    commodityBaseDTO.setPromotion(commodityPromotion);
                    // 抢购并且开启指定质保期类型的抢购
                } else if (CommodityPromotionEnum.TYPE_RUSH.getKey().equals(commodityPromotion.getType())
                        && this.commodityPromotionService.isOpenActivity(commodityBaseDTO.getGuaType(), commodityPromotion)) {

                    // "专题课"或者"系统班终端校验通过"
                    if (ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey().equals(commodityBaseDTO.getCourseType())
                            || (ClassTypeEnum.COURSE_TYPE_SYSTEM.getKey().equals(commodityBaseDTO.getCourseType())
                            && this.terminalDisplayService.terminalCheck(commodityPromotion.getId(), commodityPromotion.getType(), appType))) {

                        // 多质保期类型对应的抢购价
                        commodityPromotion.setCostPrice(OrderPriceUtil.getCommodityPrice(commodityPromotion, commodityBaseDTO.getGuaType()));
                        commodityBaseDTO.setPromotion(commodityPromotion);
                    }

                }
            }
        // 兼容老版本
        } else {
            commodityPromotion = this.commodityPromotionService.isShareCommodity(commodityDTO.getId(), user.getId(), appType);
            // 分享购
            if (null != commodityPromotion && commodityPromotion.getId() != null) {
                commodityBaseDTO.setPromotion(commodityPromotion);
                // 活动
            } else {
                if (ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey().equals(commodityDTO.getCourseType())) {
                    commodityPromotion = commodityPromotionService.selectLiveCommodityPromotion(commodityDTO.getId(), Sales.PANIC.getState(), appType);
                    commodityBaseDTO.setPromotion(commodityPromotion);
                }
            }
        }

        ClassType classType = commodityDTO.getClassType();
        // return 返回结果
        CommodityResultDTO resultDTO = new CommodityResultDTO();
        resultDTO.setSku(commodityDTO.getSkuId());// sku
        this.setZeroDiscount(commodityBaseDTO, classType);// 零元购标记
        this.confirmOldStudentSale(classType.getBenefitMark(), classType.getBenefitPrice(), user.getId(), resultDTO);// 老生优惠
        this.discountSwitch(commodityPromotion, classType, resultDTO);// 优惠券开关
        resultDTO.setCommodity(commodityBaseDTO);
        return resultDTO;
    }

    /**
     * 校验活动有效性
     * */
    private boolean checkCommodityPromotion(CommodityPromotion commodityPromotion) {
        // 下架
        if (null == commodityPromotion || Status.INVALID.getState() == commodityPromotion.getStatus()) {
            return false;
        }
        Date now = new Date();

        // 活动时间校验
        if (now.before(commodityPromotion.getStartDate()) || now.after(commodityPromotion.getEndDate())) {
            return false;
        }

        if (CommodityPromotionEnum.TYPE_RUSH.getKey().equals(commodityPromotion.getType())) {
            if (commodityPromotion.getMaximum() > -1) {
                return commodityPromotion.getMaximum() > commodityPromotion.getSales();
            }
        }
        return true;
    }

    /**
     * 确认订单返回信息
     *
     * @param * @param p 加密参数
     * @param * @param user 当前用户
     * @Author: Hero
     * @Date: Created in 11:17 2018/5/17
     */
    @Override
    public CommodityResultDTO orderConfirm(HttpServletRequest request, PayOrderCommodityParam param, Users user, Integer appType) {
        //类型
        Integer type = param.getType();
        //单品
        if (CommodityEnum.COMMODITY_TYPE_SINGLE.getKey().equals(type)) {
            return this.confirmCommodityOrder(request, param, user, appType);

            //套餐或者组合
        } else if (CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(type) || CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey().equals(type)) {
            return confirmSpecialOrder(request, param, user, appType);
        }
        return new CommodityResultDTO();
    }

    /**
     * 升级订单确认
     *
     * @param request
     * @param param
     * @param user
     * @param appType
     * @return
     */
    @Override
    public UpgradeOrderConfirmDTO orderUpgradeConfirm(HttpServletRequest request, UpgradeParam param, Users user, Integer appType) {

        ClassUpgrade classUpgrade = this.classUpgradeService.findById(param.getUpgradeId());

        if (classUpgrade == null) {
            throw new IllegalArgumentException("参数错误，无法找到升级记录（ClassUpgrade），param：" + JSON.toJSONString(param));
        }

        // 升级后的班级信息
        Classes afterClass = classesService.findById(classUpgrade.getAfterClassid());
        ClassType afterClassType = classTypeService.findById(afterClass.getClassTypeId());

        UpgradeOrderConfirmDTO dto = new UpgradeOrderConfirmDTO();
        dto.setSku(afterClassType.getSku());
        dto.setUpgradeId(classUpgrade.getId());
        dto.setShowDiscount(Objects.equals(afterClassType.getCoupon(), 1));
        dto.setTitle(afterClassType.getTitle());
        dto.setWebImg(afterClassType.getCoverUrlTwo());
        dto.setAppImg(afterClassType.getCoverUrlThree());
        dto.setComId(this.commodityProductService.selectLiveCommodityComId(classUpgrade.getAfterClassid()));

        // 取升级数据的地址
        afterClassType.setAddressMark(classUpgrade.getAddressMark());
        dto.setAgreements(this.classTypeService.getAggrements(afterClassType));

        dto.setClassTypeId(afterClassType.getId());
        boolean isMail = this.isMail(classUpgrade.getAddressMark());
        dto.setShowStudyPack(isMail);
        if (isMail) {
            dto.setStudyPackagePrice(classUpgrade.getStudyPackPrice());
            dto.setCostPrice(new BigDecimal(classUpgrade.getPrice()).add(new BigDecimal(classUpgrade.getStudyPackPrice())).doubleValue());
        } else {
            dto.setCostPrice(classUpgrade.getPrice());
        }

        return dto;
    }

    private CommodityBaseDTO orderConfirmCommodityBaseDto(CommodityDTO commodityDTO) {
        ClassType classType = commodityDTO.getClassType();

        CommodityBaseDTO commodityBaseDTO = new CommodityBaseDTO();

        commodityBaseDTO.setComId(commodityDTO.getId());
        commodityBaseDTO.setName(commodityDTO.getName());
        commodityBaseDTO.setAgreements(classTypeService.getAggrements(classType));
        commodityBaseDTO.setHasStudyPack(classType.getAddressMark());
        commodityBaseDTO.setClassTypeId(commodityDTO.getClassTypeId());
        commodityBaseDTO.setCourseType(commodityDTO.getCourseType());
        //设置web、wap封面图片
        commodityBaseDTO.setWebImg(classType.getCoverUrlTwo());
        commodityBaseDTO.setAppImg(classType.getCoverUrlThree());

        //学习包价格
        Double bookPrice = this.isMail(classType.getAddressMark()) ? classType.getBookPrice() : 0.0;
        commodityBaseDTO.setStudyPackPrice(bookPrice);
        commodityBaseDTO.setHasStudyPack(classType.getAddressMark());

        commodityBaseDTO.setGuaMul(commodityDTO.getGuaMul());
        commodityBaseDTO.setGuaType(commodityDTO.getGuaType());

        commodityBaseDTO.setBookType(classType.getBookType());
        commodityBaseDTO.setBookTypeChecked(commodityDTO.getBookTypeChecked());

        /******************************************保险******************************************/
        AggrementTemplate aggrementTemplate = commodityDTO.getAggrementTemplate();
        if (null != aggrementTemplate) {
            InsuranceDTO insuranceDTO = new InsuranceDTO(); //设置保险相关信息
            insuranceDTO.setId(aggrementTemplate.getId());
            insuranceDTO.setChecked(1);
            insuranceDTO.setPrice(aggrementTemplate.getExpense()); //设置保险价格
            commodityBaseDTO.setInsurance(insuranceDTO);
        }
        /******************************************保险******************************************/
        // 保险+学习包价格
        BigDecimal insurancePriceAndStudyPackPrice = new BigDecimal(commodityBaseDTO.getInsurancePrice()).add(new BigDecimal(commodityBaseDTO.getStudyPackPrice()));
        // 确认订单价格(保险价格+学习包价格+销售价格)
        commodityBaseDTO.setCostPrice(insurancePriceAndStudyPackPrice.add(new BigDecimal(commodityDTO.getComCostPrice())).doubleValue());
        commodityBaseDTO.setRealpayPrice(insurancePriceAndStudyPackPrice.add(new BigDecimal(commodityDTO.getComRealPrice())).doubleValue());

        return commodityBaseDTO;
    }

    /**
     * 拆分子订单
     *
     * @param parentOrder 父订单
     * @param price       拆单价格
     * @return
     */
    public PayOrder generateChildOrder(PayOrder parentOrder, Double price) {
        if (parentOrder == null) {
            return null;
        }
        PayOrder payOrder = new PayOrder();
        payOrder.setUserId(parentOrder.getUserId());
        payOrder.setOrderType(OrderEnum.ORDER_TYPE_SUB.getKey());
        payOrder.setOrderTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        payOrder.setOrderNum(parentOrder.getOrderNum());
        payOrder.setPayNum(parentOrder.getPayNum());
        payOrder.setPayStatus(parentOrder.getPayStatus());
        //payOrder.setPayType(parentOrder.getPayType());
        payOrder.setProgramName(parentOrder.getProgramName());
        payOrder.setProgramId(parentOrder.getProgramId());
        payOrder.setCategoryName(parentOrder.getCategoryName());
        payOrder.setCategoryId(parentOrder.getCategoryId());
        payOrder.setRealpayPrice(price);
        payOrder.setCostPrice(price);
        payOrder.setPayTime(parentOrder.getPayTime());
        payOrder.setRemark(parentOrder.getRemark());
        payOrder.setType(parentOrder.getType());
        payOrder.setSource(parentOrder.getSource());
        payOrder.setOs(parentOrder.getOs());
        payOrder.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        payOrder.setPhone(parentOrder.getPhone());
        payOrder.setQq(parentOrder.getQq());
        payOrder.setPayDiscountDetailId(parentOrder.getPayDiscountDetailId());
        payOrder.setVoucherOrder(parentOrder.getVoucherOrder());
        payOrder.setVoucherDetailId(parentOrder.getVoucherDetailId());
        payOrder.setParentId(parentOrder.getParentId());
        payOrder.setAuthorityUserId(parentOrder.getAuthorityUserId());
        payOrder.setTeacherid(parentOrder.getTeacherid());
        payOrder.setTeachername(parentOrder.getTeachername());
        payOrder.setDeadline(parentOrder.getDeadline());
        payOrder.setVoucher(parentOrder.getVoucher());
        payOrder.setAuditStatus(parentOrder.getAuditStatus());
        payOrder.setBackContent(parentOrder.getBackContent());
        payOrder.setBackPrice(parentOrder.getBackPrice());
        payOrder.setIp(parentOrder.getIp());
        payOrder.setAddress(parentOrder.getAddress());
        payOrder.setDeleteMark(parentOrder.getDeleteMark());
        payOrder.setOrderParentId(parentOrder.getId());
        payOrder.setOrderBatch(0);
        payOrder.setRate(0d);
        return payOrder;
    }

    @Override
    public List<PayOrder> findPayStatusNonOrder(List<Long> orderIds) {

        return this.payOrderMapper.findPayStatusNonOrder(orderIds);
    }

    /**
     * 订单创建成功返回信息
     */
    @Override
    public OrderCreateSuccessDTO findOrderCreateSuccessInfo(String payNum) {
        PayOrder payOrder = this.findPayOrderByPayNum(payNum);
        if (null != payOrder) {
            OrderCreateSuccessDTO dto = new OrderCreateSuccessDTO();
            dto.setCommodityNames(this.payOrderDetailService.selectOrderCommodityName(payOrder.getId()));
            dto.setCostPrice(payOrder.getCostPrice());
            dto.setPayNum(payOrder.getPayNum());
            dto.setOrderId(payOrder.getId());
            dto.setSku(payOrder.getCategoryId().longValue());
            dto.setClassId(this.classesService.findClassId(payOrder));
            return dto;
        }
        return null;
    }

    /**
     * 订单支付成功返回信息
     */
    @Override
    public OrderCreateSuccessDTO findOrderPaySuccessInfo(String orderNum) {
        OrderCreateSuccessDTO dto = new OrderCreateSuccessDTO();
        PayOrderTrans trans = transService.findBy("payNum", orderNum);
        if (null != trans) {
            PayOrder order = this.findById(trans.getOrderId());

            boolean paid = true;
            String payType = order.getPayType();
            Long orderParentId = order.getOrderParentId();
            if (!Objects.equals(order.getOrderParentId(), Long.valueOf(0))) {

                order = this.findById(order.getOrderParentId());
                payType = order.getPayType();
                List<PayOrder> subOrderList = this.payOrderMapper.findChildsPayOrder(orderParentId);
                for (PayOrder subOrder : subOrderList) {
                    if (!StringUtils.equals(PayStatus.PAY_STATUS_SUCCESS.getState(), subOrder.getPayStatus())) {
                        paid = false;
                        break;
                    }
                }
            }

            dto.setPaid(paid);
            dto.setPayType(payType);
            dto.setSku(order.getCategoryId().longValue());
            dto.setPayNum(order.getPayNum());
            dto.setCostPrice(trans.getPrice());
            dto.setClassId(classStudentService.findCSByOrderId(order.getId()));
            dto.setCommodityNames(this.payOrderDetailService.selectOrderCommodityName(order.getId()));
            dto.setPayTime(trans.getTransDate());
            return dto;
        }
        return null;
    }

    /**
     * 获取子订单订单号
     *
     * @param parentPayNum
     * @param num
     */
    private String getChildOrderNum(String parentPayNum, Integer num) {
        StringBuilder sb = new StringBuilder(parentPayNum);
        if (num < 10) {
            sb.append("-0");
        } else {
            sb.append("-");
        }
        return sb.append(num).toString();
    }

    /**
     * 是否需要邮寄
     */
    private boolean isMail(Integer addressMark) {
        return addressMark != null && addressMark.equals(StudyPackage.VALID.getState());
    }

    /**
     * 组合满减策略
     */
    private Double discountPrice(CommoditySpecial special, List<CommodityDTO> commodityDTOList) {
        if (null == special.getDiscountPrice() || "".equals(special.getDiscountPrice())) {
            return 0d;
        }
        Map<String, String> price = JSON.parseObject(special.getDiscountPrice(), Map.class);

        int size = commodityDTOList.size();

        if (null == price || !price.containsKey(size + "")) {
            return 0d;
        }

        return Double.valueOf(price.get(size + ""));

    }


    /**
     * 获取参数中的保险数据(都是已选择的)
     */
    private List<AggrementTemplate> checkInsuranceParams(List<CommodityParam> orderCommodityParams) {
        List<AggrementTemplate> templates = new ArrayList<>();
        if (null != orderCommodityParams) {
            int size = orderCommodityParams.size();
            for (int i = 0; i < size; i++) {
                CommodityParam orderCommodityParam = orderCommodityParams.get(i);
                // 选中
                if (this.insuranceIsChecked(orderCommodityParam.getInsurance())) {

                    AggrementTemplate aggrementTemplate = this.aggrementTemplateService.findInsuranceAggrementTemplate(orderCommodityParam.getComId(), orderCommodityParam.getInsurance().getId());
                    if (null == aggrementTemplate) {
                        throw new CheckPayOrderParamException("保险数据被篡改>>>商品ID>>>" + orderCommodityParam.getComId() + ">>>保险ID>>>" + orderCommodityParam.getInsurance().getId() + ">>>保险选中状态>>>" + !OrderEnum.INSURANCE_UN_CHECKED.getKey().equals(orderCommodityParam.getInsurance().getChecked()));
                    }
                    templates.add(aggrementTemplate);
                }
            }
        }

        return templates;
    }

    /**
     * 保险是否选中
     */
    private boolean insuranceIsChecked(InsuranceParam insuranceParam) {
        if (null == insuranceParam) {
            return false;
        }
        // checked为null 或者 checked等于选中状态都是选中
        if (insuranceParam.getChecked() == null || OrderEnum.INSURANCE_CHECKED.getKey().equals(insuranceParam.getChecked())) {
            return true;
        }
        return false;
    }

    /**
     * 商品总价格
     */
    private Double getCommodityTotalPrice(List<CommodityDTO> commodityDTOList) {
        BigDecimal commodityPrice = new BigDecimal(0);
        if (null != commodityDTOList) {
            for (CommodityDTO commodityDTO : commodityDTOList) {
                commodityPrice = commodityPrice.add(new BigDecimal(commodityDTO.getComCostPrice()));
            }

        }
        return commodityPrice.doubleValue();
    }

    /**
     * 校验下单商品ID是否正确
     */
    private void checkCreateOrderComId(List<CommodityDTO> commodityDTOList, List<CommodityParam> params) {
        if (null == commodityDTOList) {
            throw new CheckPayOrderParamException("数据被篡改>>>下单请求参数>>>" + JSON.toJSONString(params));
        } else if (commodityDTOList.size() != params.size()) {
            throw new CheckPayOrderParamException("数据被篡改>>>下单请求参数>>>" + JSON.toJSONString(params));
        }
    }

    private void setZeroDiscount(CommodityBaseDTO commodityBaseDTO, ClassType classType) {
        // 是否是零元购
        if (Constant.DISCOUNT_USE_ACCESS.equals(classType.getCoupon()) && Constant.DISCOUNT_USE_ZERO_PAY.equals(classType.getHasLimit())) {
            commodityBaseDTO.setZeroStatus(1);
        } else {
            commodityBaseDTO.setZeroStatus(0);
        }
    }


    /**
     * 优惠券、码 开关
     *
     */
    private void discountSwitch(CommodityPromotion commodityPromotion, ClassType classType, CommodityResultDTO resultDTO) {
        if (null == commodityPromotion) {
            resultDTO.setDiscount(ClassTypeEnum.DISCOUNT_ZREO.getKey());
        } else {
            //免费，不可用
            if (Objects.equals(ClassTypeEnum.CHARGE_NO.getKey(), commodityPromotion.getCharge())) {
                resultDTO.setDiscount(ClassTypeEnum.DISCOUNT_ZREO.getKey());
                //收费，并且开关可用
            } else {
                this.discountSwitch(classType.getCoupon(), classType.getVoucherAble(), resultDTO);
            }
        }
    }

    /**
     * 优惠券、码 开关
     *
     * @param coupon
     * @param voucherAble 优惠券可使用类型 0:不可用 1:优惠码 2:优惠券
     */
    private void discountSwitch(Integer coupon, Integer voucherAble, CommodityResultDTO resultDTO) {
        //优惠码可用
        if (coupon == Status.VALID.getState()) {
            //discount为1:优惠码
            resultDTO.setDiscount(ClassTypeEnum.DISCOUNT_ONE.getKey());
        } else if (voucherAble == Status.VALID.getState()) {
            //discount为2优惠券可用
            resultDTO.setDiscount(ClassTypeEnum.DISCOUNT_TWO.getKey());
        } else {
            //都不可用
            resultDTO.setDiscount(ClassTypeEnum.DISCOUNT_ZREO.getKey());
        }
    }

    /**
     * 老生优惠
     * */
    private void confirmOldStudentSale(Integer benefitMark,Integer benefitPrice, Long userId, CommodityResultDTO resultDTO) {
        // 老生优惠
        if (Objects.equals(benefitMark, 1) && commodityService.validateAccordOldSale(userId)) {
            resultDTO.setOldStudentSalePrice(benefitPrice);
        }
    }
}
