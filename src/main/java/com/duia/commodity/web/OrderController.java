package com.duia.commodity.web;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.aop.CommodityPromotionAnnotation;
import com.duia.commodity.common.dto.CommodityResultDTO;
import com.duia.commodity.common.dto.OrderCreateSuccessDTO;
import com.duia.commodity.common.dto.ServiceKeyDto;
import com.duia.commodity.common.dto.UpgradeOrderConfirmDTO;
import com.duia.commodity.core.PayOrderAuthProxy;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.resultG.Result;
import com.duia.resultG.ResultCode;
import com.duia.resultG.ResultGenerator;
import com.duia.security.decrypt.Dec;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.PayOrderCommodityParam;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;
import com.duia.security.param.UpgradeParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created by 李恒名 on 2017/7/18.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = Logger.getLogger(OrderController.class);
    @Resource
    private PayDiscountDetailService discountDetailService;
    @Resource
    private UsersService usersService;
    @Resource
    private ClassesService classesService;
    @Resource
    private ClassTypeService classTypeService;
    @Resource
    private ClassStudentDetailService classStudentDetailService;
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private PayOrderDetailService payOrderDetailService;
    @Resource
    private PayOrderTransService transService;
    @Resource
    private PayOrderMailingAddressService payOrderMailingAddressService;
    @Resource
    private PayOrderAuthProxy payOrderAuthProxy;
    @Resource
    private ClassStudentService classStudentService;


    @PostMapping("upgrade/discount/check")
    public Result upgradeDiscountCheck(@RequestParam String discountCode, @RequestParam Long upgradeId) {
        return this.discountDetailService.checkDiscount(null, discountCode, null, null, upgradeId);
    }

    @PostMapping("/discount/check")
    public Result discountCheck(@RequestParam String discountCode, @RequestParam Long comId, @SessionAttribute Users user,@RequestParam Long specialId) {
        return this.discountDetailService.checkDiscount(user, discountCode, comId, specialId, null);
    }
    /**
     * 更新订单支付方式,给app用
     *
     * @param id
     * @param payType
     * @return
     */
    @PostMapping("/updatePayTypeById")
    public Result discountCheck(@RequestParam Long id, @RequestParam String payType) {
        Result result;
        PayOrder payOrder = payOrderService.findById(id);
        payOrder.setPayType(payType);
        payOrderService.update(payOrder);
        transService.saveTrans(payOrder);
        result = ResultGenerator.genSuccessResult(payOrder);
        return result;
    }

    /**
     * 查询智齿服务
     *
     * @param classId 班级id
     * @param userId  用户id
     * @return
     */
    @PostMapping("/serviceKey")
    public Result serviceKey(@RequestParam Long classId, @RequestParam Long userId) {

        ServiceKeyDto serviceDto = new ServiceKeyDto();
        Classes classes = classesService.findById(classId);
        ClassType classType = classTypeService.findById(classes.getClassTypeId());
        serviceDto.setHasService(classType.getHasService());
        if (Objects.equals(classType.getHasService(), 1)) {
            Users users = usersService.findById(userId);
            serviceDto.setMobile(users.getMobile());
            ClassStudentDetail classStudentDetail = classStudentDetailService.findClassStudentDetailByUserId(userId.intValue());
            serviceDto.setName(StringUtils.isNotBlank(classStudentDetail.getName()) ? classStudentDetail.getName() : users.getUsername());
            serviceDto.setTitle(classType.getTitle());
            serviceDto.setServiceKey(classType.getServiceKey());
            serviceDto.setKeyType(classType.getKeyType());
            serviceDto.setRobotId(classType.getRobotId());
            serviceDto.setReceptionMode(classType.getReceptionMode());
            serviceDto.setClassNo(classes.getClassNo());
        }
        return ResultGenerator.genSuccessResult(serviceDto);
    }

    /**
     * 创建订单
     *
     * @param request
     * @param p
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Result commodityOrder(HttpServletRequest request, String p) {
        try {
            // p参数解密
            PayOrderParam param = Dec.P(PayOrderParam.class, p);

            // 验签
            if (!Dec.vSign(param)) {
                return ResultGenerator.genCustomResult(ResultCode.UNAUTHORIZED.code, "验签失败", null);
            }

            // 创建订单信息
            PayOrder payOrder = payOrderAuthProxy.auth(request, param);

            return ResultGenerator.genSuccessResult(payOrder);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ResultGenerator.genCustomResult(ResultCode.UNAUTHORIZED.code, "验签失败", null);
        } catch (CheckPayOrderParamException ex) {
            ex.printStackTrace();
            return ResultGenerator.genFailResult(StringUtils.isEmpty(ex.getMessage()) ? "参数校验失败" : ex.getMessage());
        }
    }

    /**
     * ios下单
     * */
    @PostMapping("/createOrderIOS")
    @ResponseBody
    public com.duia.resultG.Result createOrderIOS(HttpServletRequest request, @SessionAttribute Users user, String p) {
        try {
            com.duia.resultG.Result result = this.payOrderService.createOrderIOS(request, user, p);
            // 零元购set班级ID
            this.iosOrderSetClassId(result);

            return result;
        } catch (CheckPayOrderParamException ex) {
            ex.printStackTrace();
            return com.duia.resultG.ResultGenerator.genFailResult(StringUtils.isEmpty(ex.getMessage()) ? "参数校验失败" : ex.getMessage());
        }
    }

    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    @ResponseBody
    public Result createOrder(HttpServletRequest request, @SessionAttribute Users user, String p) {
        try {
            // p参数解密
            PayOrderCommodityParam param = Dec.P(PayOrderCommodityParam.class, p);


            PayOrderParam orderParam = new PayOrderParam();

            orderParam.setSource(Constant.ORDER_SOURCE_WEB);
            orderParam.setC(param);

            PayOrderInfoParam payOrderInfoParam = new PayOrderInfoParam();
            payOrderInfoParam.setUserId(user.getId());
            orderParam.setP(payOrderInfoParam);

            return ResultGenerator.genSuccessResult(new OrderCreateSuccessDTO(payOrderAuthProxy.auth(request, orderParam)));
        } catch (CheckPayOrderParamException ex) {
            ex.printStackTrace();
            return ResultGenerator.genFailResult("参数校验失败");
        }
    }
    /**
     * 创建升级订单
     * */
    @PostMapping("/createUpgradeOrder")
    @ResponseBody
    public Result createUpgradeOrder(HttpServletRequest request, @SessionAttribute Users user, String p) {
        try {
            // p参数解密
            UpgradeParam param = Dec.P(UpgradeParam.class, p);

            PayOrderParam orderParam = new PayOrderParam();

            orderParam.setSource(Constant.ORDER_SOURCE_WEB);
            orderParam.setU(param);
            orderParam.setType(com.duia.security.util.Constant.PAYORDERPARAM_TYPE_ADD_U);

            PayOrderInfoParam payOrderInfoParam = new PayOrderInfoParam();
            payOrderInfoParam.setUserId(user.getId());
            orderParam.setP(payOrderInfoParam);

            return ResultGenerator.genSuccessResult(new OrderCreateSuccessDTO(payOrderAuthProxy.auth(request, orderParam)));
        } catch (CheckPayOrderParamException ex) {
            ex.printStackTrace();
            return ResultGenerator.genFailResult("参数校验失败");
        }
    }

    /**
     * 确认订单-新
     */
    @CommodityPromotionAnnotation(describe = "分享购>>>appTyp纠正")
    @PostMapping("/confirmNew")
    @ResponseBody
    public Result orderConfirmNew(HttpServletRequest request, @SessionAttribute Users user,String p, Integer appType) {

        PayOrderCommodityParam param = Dec.P(PayOrderCommodityParam.class, p);

        CommodityResultDTO commodityResultDTO = payOrderService.orderConfirm(request, param, user, appType);

        return ResultGenerator.genSuccessResult(commodityResultDTO);
    }

    /**
     * 升级订单-新
     */
    @PostMapping("/upgradeConfirm")
    @ResponseBody
    public Result orderUpgradeConfirm(HttpServletRequest request, @SessionAttribute Users user, String p, Integer appType) {

        UpgradeParam param = Dec.P(UpgradeParam.class, p);

        UpgradeOrderConfirmDTO dto = payOrderService.orderUpgradeConfirm(request, param, user, appType);

        return ResultGenerator.genSuccessResult(dto);
    }

    /**
     * 订单邮寄地址
     * */
    @PostMapping("/mailAddress")
    @ResponseBody
    public Result orderMailAddress(Long orderId) {
        return ResultGenerator.genSuccessResult(this.payOrderMailingAddressService.findById(orderId));
    }

    /**
     * 订单创建成功显示信息
     *
     * @Date: 16:21 2018/6/8
     */
    @PostMapping("/orderPayInfo")
    @ResponseBody
    public Result orderPayInfo(String payNum) {

        return ResultGenerator.genSuccessResult(this.payOrderService.findOrderCreateSuccessInfo(payNum));
    }

    /**
     * 订单支付成功显示信息
     *
     * @Date: 14:14 2018/6/22
     */
    @PostMapping("/orderPaySuccessInfo")
    @ResponseBody
    public Result orderPaySuccessInfo(String orderNum) {

        return ResultGenerator.genSuccessResult(this.payOrderService.findOrderPaySuccessInfo(orderNum));
    }

    /**
     * 支付成功提示信息
     * @param payNum
     * @return
     */
    @PostMapping("/agreementTips")
    @ResponseBody
    public Result agreementTips(String payNum) {
        return ResultGenerator.genSuccessResult(this.payOrderDetailService.findPayTips(payNum));
    }



    private void iosOrderSetClassId(Result result) {
        if (result.getCode() == ResultCode.SUCCESS.code) {
            OrderCreateSuccessDTO orderCreateSuccessDTO = ((OrderCreateSuccessDTO) result.getData());

            if (Constant.PAY_TYPE_ZEROPAY.equals(orderCreateSuccessDTO.getPayType())) {
                for (int i = 0; i < 5; i++) {

                    List<ClassStudent> classStudentList = this.classStudentService.findByOrderId(orderCreateSuccessDTO.getOrderId());

                    if (null != classStudentList && !classStudentList.isEmpty()) {
                        orderCreateSuccessDTO.setClassId(classStudentList.get(0).getClassId());
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                result.setData(orderCreateSuccessDTO);
            }
        }
    }

}
