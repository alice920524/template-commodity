package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.dto.*;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.common.enums.OrderDetailEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.PayOrderDetailMapper;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.enums.AgTemplate;
import com.duia.enums.OrderType;
import com.duia.enums.Status;
import com.duia.enums.StudyPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
@Service
@Transactional
public class PayOrderDetailServiceImpl extends AbstractService<PayOrderDetail> implements PayOrderDetailService {
    @Resource
    private PayOrderDetailMapper payOrderDetailMapper;
    @Resource
    private ClassTypeService classTypeService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private AggrementTemplateService aggrementTemplateService;
    @Resource
    private ClassStudentBookService classStudentBookService;
    @Resource
    private CommodityProductService commodityProductService;
    @Resource
    private ClassesService classesService;
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private ClassUpgradeService classUpgradeService;


    @Override
    public void savePayOrderDetail(PayOrder payOrder, Object o) {
        OrderAbComp abComp = (OrderAbComp) o;
        List<CommodityDTO> commodityList = abComp.commoditys();
        for (int i = 0; i < commodityList.size(); i++) {
            CommodityDTO commodityDTO = commodityList.get(i);
            ClassType classType = commodityDTO.getClassType();

            PayOrderDetail payOrderDetail = this.makeLiveOrderDetail(payOrder, commodityDTO);

            this.save(payOrderDetail);

            // 保存图书信息 - 需要区分升级和非升级
            this.classStudentBookService.savePayOrderBook(payOrderDetail, payOrder.getUserId(), commodityDTO, abComp);

            // 配置保险、保过、质保期、退款协议相关信息
            aggrementTemplateService.saveClassStudentAgTemplate(payOrder, payOrderDetail, classType, commodityDTO.getAggrementTemplate());

            // 配置专属视频详情
            this.makeExclusiveOrderDetail(payOrder, commodityDTO);
        }
    }

    @Override
    public List<String> selectOrderCommodityName(Long orderId) {
        return this.payOrderDetailMapper.selectOrderCommodityName(orderId);
    }

    /**
     * 支付成功提示信息
     *
     * @param payNum
     * @return
     */
    @Override
    public List<PayTipsDTO> findPayTips(String payNum) {
        if (null == payNum) {
            return new ArrayList<>();
        }
        int index = payNum.indexOf("-");
        // 分期
        if (index > -1) {
            payNum = payNum.substring(0, index);
        }
        //通过订单号查询协议
        List<PayOrderDetail> payOrderDetails = payOrderDetailMapper.selectTipsByPayNum(payNum);
        String type;
        if (null == payOrderDetails || payOrderDetails.isEmpty()) {
            return new ArrayList<>();
        }
        PayOrder payOrder  = payOrderService.findById(payOrderDetails.get(0).getOrderId());


        // 订单中所有的商品信息
        List<PayTipsDTO> resultPayTips = new ArrayList<>(6);

        // 班级信息
        this.setClassInfo(payOrderDetails, resultPayTips,1,payOrder);

        //温馨提示中的人脸识别
        this.setFaceRecognition(payOrderDetails, resultPayTips, 99, 2);

        // 协议的内容 agreementType:协议类型 order:排序
        // 质保期协议
        this.setAgreementTips(payOrderDetails, resultPayTips, AgTemplate.WARRANTY.getState(), 3);
        // 退款协议
        this.setAgreementTips(payOrderDetails, resultPayTips, AgTemplate.REFUND.getState(), 4);
        // 七天价保协议
        this.setAgreementTips(payOrderDetails, resultPayTips, AgTemplate.PROTECT.getState(), 5);
        // 保过协议
        this.setAgreementTips(payOrderDetails, resultPayTips, AgTemplate.CURRICULUM.getState(), 6);


        return resultPayTips;
    }

    //设置人脸识别  state = 99状态      order = 2排序
    private void setFaceRecognition(List<PayOrderDetail> payOrderDetails, List<PayTipsDTO> resultPayTips, int state, int order) {
        //名称和内容数组
        List<AggrementTipsDTO> list = new ArrayList<AggrementTipsDTO>();
        List<String> name = new ArrayList<String>();
        //判断是否有人脸识别
        for (PayOrderDetail face : payOrderDetails) {
            //如果有人脸识别，返回课程名称
            if (ClassTypeEnum.FACE_RECOGNITION_OPEN.getKey().equals(face.getFaceRecognition())) {
                String comName = face.getComName();
                name.add(comName);
            }
        }

        PayTipsDTO payTips = new PayTipsDTO();
        if (!name.isEmpty()) {
            list.add(new AggrementTipsDTO(name));
        }
        payTips.setOrder(order);
        payTips.setStatus(state);
        payTips.setComNumber(payOrderDetails.size());
        payTips.setTips(list);
        resultPayTips.add(payTips);

    }

    private void setAgreementTips(List<PayOrderDetail> payOrderDetails, List<PayTipsDTO> resultPayTips, Integer agreementType, int order) {
        Integer size = payOrderDetails.size();

        Map<Long, AggrementTipsDTO> agreementContentMap = new LinkedHashMap<>(size);

        // AggrementTips 数据查找
        // 普通商品
        this.setAgreementTips(agreementContentMap, payOrderDetails, OrderEnum.BUY_TYPE_COMMON.getKey(), agreementType);
        // 加价购商品
        this.setAgreementTips(agreementContentMap, payOrderDetails, OrderEnum.BUY_TYPE_ADD_MONEY.getKey(), agreementType);
        Collection agreementContent = agreementContentMap.values();

        if (null == agreementContent || agreementContent.isEmpty()) {
            return;
        }

        PayTipsDTO payTips = new PayTipsDTO();
        payTips.setOrder(order);
        payTips.setStatus(agreementType);
        payTips.setComNumber(payOrderDetails.size());

        // AggrementTips 数据组装
        payTips.setTips(agreementContent);

        resultPayTips.add(payTips);
    }

    private void setAgreementTips(Map<Long, AggrementTipsDTO> agreementMap, List<PayOrderDetail> payOrderDetails, Integer buyType, Integer agreementType) {

        for (PayOrderDetail payOrderDetail : payOrderDetails) {
            // 匹配  普通商品、加价购商品
            if (buyType.equals(payOrderDetail.getBuyType())) {
                // 订单详情-协议类型
                AggrementTemplate aggrementTemplate = this.aggrementTemplateService.findByOrderDetailId(payOrderDetail.getId(), agreementType);
                if (null != aggrementTemplate) {
                    // 相同协议去重
                    if (agreementMap.containsKey(aggrementTemplate.getId())) {
                        AggrementTipsDTO aggrementTips = agreementMap.get(aggrementTemplate.getId());
                        aggrementTips.getComName().add(payOrderDetail.getComName());
                    } else {
                        List<String> names = new ArrayList<>();
                        names.add(payOrderDetail.getComName());
                        agreementMap.put(aggrementTemplate.getId(), new AggrementTipsDTO(names, aggrementTemplate.getContent()));
                    }
                }
            }
        }
    }


    /**
     * 班级信息
     */
    private void setClassInfo(List<PayOrderDetail> payOrderDetails, List<PayTipsDTO> resultPayTips, Integer order,PayOrder payOrder) {

        List<ClassInfoTipsDTO> deadlineTipsList = new ArrayList<>(payOrderDetails.size());
        //获取type
        String type = payOrder.getType();

        // 正常购买购买
        this.setClassInfoTipsByBuyType(deadlineTipsList, payOrderDetails, OrderEnum.BUY_TYPE_COMMON.getKey(),type);

        // 加价购购买
        this.setClassInfoTipsByBuyType(deadlineTipsList, payOrderDetails, OrderEnum.BUY_TYPE_ADD_MONEY.getKey(),type);

        // 班级信息
        resultPayTips.add(new PayTipsDTO(deadlineTipsList, 0, order, payOrderDetails.size()));
    }

    // 正常购买订单商品、加价购购买订单商品
    private void setClassInfoTipsByBuyType(List<ClassInfoTipsDTO> list, List<PayOrderDetail> payOrderDetails, Integer buyType, String type) {
        for (PayOrderDetail payOrderDetail : payOrderDetails) {
            if (buyType.equals(payOrderDetail.getBuyType())) {
                //查询质保期
                ClassTypeDeadline classTypeDeadlinre = classTypeService.getClassTypeDeadline(payOrderDetail.getClassTypeId(), payOrderDetail.getMode(), payOrderDetail.getValidity(), payOrderDetail.getGuaType());

                Classes classes = this.getClassesByComIdAndOrderType(payOrderDetail.getComId(), type);

                ClassInfoTipsDTO classInfoTipsDTO = new ClassInfoTipsDTO(payOrderDetail.getComName(), payOrderDetail.getGuaType(), payOrderDetail.getAllowGua(), classTypeDeadlinre.getDeadline(), classTypeDeadlinre.getDelayFirst(), classTypeDeadlinre.getDelaySecond());

                //返回有无结课时间 0无 1有
                if (classes == null || classes.getEndDate() == null || Integer.valueOf(2).equals(classes.getClassType())) {
                    classInfoTipsDTO.setHasEndDate(0);
                } else {
                    classInfoTipsDTO.setHasEndDate(1);
                    classInfoTipsDTO.setClassEnd(classes.getClassEnd());
                }
                // 组装数据
                list.add(classInfoTipsDTO);
            }
        }
    }

    /**
     * 查询班级信息
     * comId:商品ID or class_upgrade主键ID
     * type:订单类型
     * */
    private Classes getClassesByComIdAndOrderType(Long comId, String type) {
        // 升级
        if (OrderType.U.getState().equals(type)) {
            //升级
            ClassUpgrade classUpgrade = classUpgradeService.findById(comId);
            return classesService.findById(classUpgrade.getAfterClassid());
            // 非升级
        } else {
            Long classesId = commodityProductService.selectLiveCommodityClassId(comId);
            if (null == classesId) {
                return new Classes();
            }

            return classesService.findById(classesId);
        }
    }

    private PayOrderDetail makeLiveOrderDetail(PayOrder payOrder, CommodityDTO commodity) {
        ClassType classType = commodity.getClassType();
        AggrementTemplate aggrementTemplate = commodity.getAggrementTemplate();

        //构建订单详情
        PayOrderDetail detail = new PayOrderDetail();
        detail.setComNum(1); // 商品数量
        detail.setComId(commodity.getId());
        detail.setComName(commodity.getName());
        detail.setComCostPrice(commodity.getComCostPrice());
        detail.setComRealPrice(commodity.getComRealPrice());
        detail.setGuaType(commodity.getGuaType());

        detail.setBuyType(commodity.getBuyType());
        detail.setComMode(commodity.getComMode());
        detail.setSpecialComId(commodity.getSpecialId());

        ClassTypePriceConfigureDTO classTypePriceConfigureDTO = this.classTypeService.getClassTypePriceConfigure(classType, commodity.getGuaType());
        detail.setAllowGua(classTypePriceConfigureDTO.getGua());
        detail.setAllowDelay(classTypePriceConfigureDTO.getDelay());

        detail.setComCoverUrl(commodity.getCoverUrl());
        detail.setComType(commodity.getType());
        detail.setGifts(0); // 非赠品
        detail.setAddressMark(commodity.getAddressMark());
        detail.setStudyPackagePrice(commodity.getBookPrice());//学习包金额(正常购买 or 加价购商品)
        detail.setCourseType(commodity.getCourseType());// 班型类型

        detail.setOrderId(payOrder.getId());
        detail.setClassTypeId(classType.getId());
        detail.setClassTypeValidity(classType.getValidity());//班级有效期
        detail.setValidity(classType.getValidity());
        detail.setMode(classType.getMode());
        detail.setFaceRecognition(classType.getFaceRecognition());// 人脸识别

        if (null != aggrementTemplate && null != aggrementTemplate.getExpense()) {
            detail.setInsurancePrice(aggrementTemplate.getExpense());
        }


        this.setPayOrderDetailBookType(payOrder, commodity, classType, detail);//bookType

        // 非特权订单、班型开启产生特权开关
        if (OrderEnum.VOUCHER_ORDER_PT.getKey().equals(payOrder.getVoucherOrder()) && ClassTypeEnum.VOUCHER_OPEN.getKey().equals(classType.getVoucher())) {
            detail.setVoucherId(classType.getVoucherId());
        }

        this.setAgreementArrays(detail, classType, aggrementTemplate);

        return detail;
    }

    private void makeExclusiveOrderDetail(PayOrder payOrder, CommodityDTO commodityDTO) {
        ClassType classType = commodityDTO.getClassType();
        //专属视频课id集合
        List<Long> ids = JSON.parseArray(classType.getExclusiveCourses(), Long.class);
        if (ids != null && !ids.isEmpty()) {
            List<Commodity> exclusives = commodityService.findByIdList(ids);
            for (Commodity commodity : exclusives) {
                //构建订单详情
                PayOrderDetail detail = new PayOrderDetail();
                detail.setComNum(1); // 商品数量
                detail.setComId(commodity.getId());
                detail.setComName(commodity.getName());
                detail.setComCostPrice(commodity.getCostPrice());
                detail.setComCoverUrl(commodity.getCoverUrl());
                detail.setComType(commodity.getType());

                detail.setOrderId(payOrder.getId());
                detail.setClassTypeId(classType.getId());
                detail.setClassTypeValidity(classType.getValidity());//班级有效期
                detail.setComMode(commodityDTO.getComMode());
                detail.setSpecialComId(commodityDTO.getSpecialId());
                detail.setBuyType(0);//正常购买
                detail.setGifts(1);//是赠品
                this.save(detail);
            }
        }
    }

    /**
     * 设置协议icon
     *
     * @param orderDetail
     * @param classType
     */
    private void setAgreementArrays(PayOrderDetail orderDetail, ClassType classType, AggrementTemplate insuranceMap) {
        List<Integer> agreements = new ArrayList<>();
        // 添加学习包icon 订单详情里面含有学习包邮寄信息,加价购需要双重判断,普通商品需要班型判断
        if (Objects.equals(StudyPackage.VALID.getState(), orderDetail.getAddressMark())) {
            agreements.add(AgTemplate.BOOK.getState());
        }

        // 正常购买并且已购买保险
        if (OrderEnum.BUY_TYPE_COMMON.getKey().equals(orderDetail.getBuyType()) && insuranceMap != null) {
            if (Objects.equals(Status.VALID.getState(), classType.getInsurance())
                    && checkedInsurance()) {
                agreements.add(AgTemplate.INSURANCE.getState());
            }
        }

        if (Objects.equals(Status.VALID.getState(), classType.getGuaranteeAggrement())
                && checkedInsurance()) {// 保过
            agreements.add(AgTemplate.CURRICULUM.getState());
        }
        if (Objects.equals(Status.VALID.getState(), classType.getGuaranteeStatus())
                && checkedInsurance()) {// 质保期
            agreements.add(AgTemplate.WARRANTY.getState());
        }
        if (Objects.equals(Status.VALID.getState(), classType.getRefundStatus())
                && checkedInsurance()) {// 退款
            agreements.add(AgTemplate.REFUND.getState());
        }
        if (Objects.equals(Status.VALID.getState(), classType.getPriceProtect())
                && checkedInsurance()) {// 七天价保
            agreements.add(AgTemplate.PROTECT.getState());
        }
        if (!agreements.isEmpty()) {
            orderDetail.setAgreements(agreements.toString());
        }
    }

    /**
     * 需要修改
     *
     * @return
     */
    private Boolean checkedInsurance() {
        return true;
    }

    /**
     * 设置bookType值
     */
    private void setPayOrderDetailBookType(PayOrder payOrder, CommodityDTO commodity, ClassType classType, PayOrderDetail detail) {
        if (OrderEnum.MAIL.getKey().equals(payOrder.getIsMail())) {
            Integer bookTypeChecked = commodity.getBookTypeChecked();
            if (null == bookTypeChecked) {
                detail.setBookType(getDefaultBookType(classType));
            } else {
                detail.setBookType(bookTypeChecked);// 图书类型选中状态入库
            }
        }
    }

    /**
     * 根据bookType取默认值
     */
    private Integer getDefaultBookType(ClassType classType) {
        if (ClassTypeEnum.BOOK_TYPE_COMMON.getKey().equals(classType.getBookType())) {
            return OrderDetailEnum.BOOK_TYPE_COMMON.getKey();
        } else {
            return OrderDetailEnum.BOOK_TYPE_JUNIOR_SCHOOL.getKey();
        }
    }
}
