package com.duia.commodity.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sign.Base64;
import com.duia.commodity.common.Constant;
import com.duia.commodity.common.exception.RemoteInvokeException;
import com.duia.commodity.core.PayOrderAuthProxy;
import com.duia.commodity.model.*;
import com.duia.commodity.service.*;
import com.duia.constant.Constants;
import com.duia.constant.DNameConstants;
import com.duia.enums.PayStatus;
import com.duia.security.decrypt.Dec;
import com.duia.security.param.PayOrderCommodityParam;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;
import com.duia.security.param.UpgradeParam;
import com.github.kevinsawicki.http.HttpRequest;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * Created by 李恒名 on 2017/6/5.
 * <p>
 * 路由控制器，所有页面的路由写在该类下，该控制器只允许返回View，且HttpRequest方法必须为Get。
 */
@Controller
public class RouteController {
    private final Logger logger = LoggerFactory.getLogger(RouteController.class);
    @Resource
    private PayOrderTransService transService;
    @Resource
    private AggrementTemplateService aggrementTemplateService;
    @Resource
    private PayOrderMailingAddressService payOrderMailingAddressService;
    @Resource
    private PayOrderService orderService;
    @Resource
    private PayOrderDetailService payOrderDetailService;
    @Resource
    private SmsSendService smsSendService;
    @Value("${duia.domain}")
    private String duiaDomain;
    @Value("${item.domain}")
    private String itemDomain;
    @Resource
    private ClassStudentService classStudentService;
    @Resource
    private PayDiscountDetailService payDiscountDetailService;
    @Resource
    private ClassRebuildService classRebuildService;
    @Resource
    private PayOrderAuthProxy payOrderAuthProxy;
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     * 商品选择页[官网前台] - 路由
     *
     * @param request
     * @return
     */
    @GetMapping("/commodity/select/{type}")
    public ModelAndView selectRoute(HttpServletRequest request, @PathVariable("type") Integer type) {
        ModelAndView mv = new ModelAndView("privilegeAll");
        return mv;
    }

    //创建订单  路由-新
    @GetMapping("/order/newPay")
    public ModelAndView orderNewPay(HttpServletRequest request,
                                    @SessionAttribute Users user,
                                    String p,
                                    Integer appType,
                                    @RequestParam(defaultValue = "0", required = false) Integer os,
                                    @Value("${ccpsms.template.id.create-order}") String templateId) {

        // 初始化参数
        if (os == null) {
            os = 0;
        }

        ModelAndView mv = new ModelAndView();

        // p参数解密
        PayOrderCommodityParam param = Dec.P(PayOrderCommodityParam.class, p);


        PayOrderParam orderParam = new PayOrderParam();

        orderParam.setSource(Constant.ORDER_SOURCE_WEB);
        orderParam.setC(param);

        PayOrderInfoParam payOrderInfoParam = new PayOrderInfoParam();
        payOrderInfoParam.setUserId(user.getId());
        orderParam.setP(payOrderInfoParam);

        PayOrder order = payOrderAuthProxy.auth(request, orderParam);
        //发短信
        String mobile = user.getMobile();
        if (StringUtils.isNotEmpty(mobile)) {
            smsSendService.send(mobile, templateId, new String[]{""});
        }

        // 页面跳转
        this.createOrderSetViewName(mv, order, appType, os);

        return mv;
    }

    //创建升级订单
    @GetMapping("/order/upgradePay")
    public ModelAndView orderUpgradePay(HttpServletRequest request,
                                        @SessionAttribute Users user,
                                        String p,
                                        Integer appType,
                                        @RequestParam(defaultValue = "0", required = false) Integer os,
                                        @Value("${ccpsms.template.id.create-order}") String templateId) {
        // 初始化参数
        if (os == null) {
            os = 0;
        }

        ModelAndView mv = new ModelAndView();

        // p参数解密
        UpgradeParam param = Dec.P(UpgradeParam.class, p);

        PayOrderParam orderParam = new PayOrderParam();

        orderParam.setSource(Constant.ORDER_SOURCE_WEB);
        orderParam.setU(param);
        orderParam.setType(com.duia.security.util.Constant.PAYORDERPARAM_TYPE_ADD_U);

        PayOrderInfoParam payOrderInfoParam = new PayOrderInfoParam();
        payOrderInfoParam.setUserId(user.getId());
        orderParam.setP(payOrderInfoParam);

        PayOrder order = payOrderAuthProxy.auth(request, orderParam);

        //发短信
        String mobile = user.getMobile();
        if (StringUtils.isNotEmpty(mobile)) {
            smsSendService.send(mobile, templateId, new String[]{""});
        }

        // 页面跳转
        this.createOrderSetViewName(mv, order, appType, os);

        return mv;
    }

    //去结算 路由
    @GetMapping("/order/payment")
    public ModelAndView orderPay(HttpServletRequest request, String num, Integer appType,
                                 @RequestParam(defaultValue = "0", required = false) Integer os, @SessionAttribute Users user) {
        ModelAndView mv = new ModelAndView();
        PayOrder order = orderService.findBy("payNum", num);
        if (!Objects.equals(user.getId(), order.getUserId())) {//不是当前用户的订单
            mv.setViewName("payNoOrder");
            return mv;
        }

/*        if (PayOrder.PAY_STATUS_SUCCESS.equals(order.getPayStatus())) {//订单已支付过
            mv.setViewName("redirect:/pay/success?orderNum=" + order.getOrderNum());
            return mv;
        }*/

        if (Constant.PAY_TYPE_BATCH.equals(order.getPayType())) {
            mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/order/batch?payNum=" + order.getPayNum());//分批订单重定向
        } else if (Constant.PAY_TYPE_COFFEE.equals(order.getPayType())) {
            mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/order/ce?payNum=" + order.getPayNum() + "&appType=" + appType + "&os=" + os);//咖啡订单重定向
        } else if (Constant.PAY_TYPE_UMONEY.equals(order.getPayType())) {
            mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/order/bd?payNum=" + order.getPayNum() + "&appType=" + appType + "&os=" + os);//百度订单重定向
        } else if (Constant.PAY_TYPE_HAIMI.equals(order.getPayType())) {
            mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/order/hm?payNum=" + order.getPayNum() + "&appType=" + appType + "&os=" + os);//百度订单重定向
        } else {
            mv.addObject("orderId", order.getId());//商品价格
            mv.addObject("price", order.getCostPrice());//支付金额
            mv.addObject("payNum", order.getPayNum());//订单编号
            mv.addObject("comName", order.getProgramName());//商品名称
            mv.addObject("address", payOrderMailingAddressService.findById(order.getId()));//寄送地址信息
            mv.addObject("appType", appType);
            mv.addObject("os", os);
            mv.addObject("sku", order.getCategoryId());
            mv.addObject("orderInstal", order.getOrderInstal() > 0 ? 1 : 0);
            mv.addObject("commodityNames", this.payOrderDetailService.selectOrderCommodityName(order.getId()));

            mv.addObject("orderPrice", order.getRealpayPrice());//订单金额
            if (order.getPayDiscountDetailId() != null) {//使用了优惠券
                PayDiscountDetail payDiscountDetail = payDiscountDetailService.findById(order.getPayDiscountDetailId());
                mv.addObject("discountPrice", Double.valueOf(payDiscountDetail.getDiscount()));
            }
            mv.addObject("oldStuSalePrice", order.getBenefitPrice());//老生优惠金额
            mv.addObject("periodFlag", (order.getOrderInstal() != null && order.getOrderInstal() != 0) ? 1 : 0);//分期订单判断(0:不是分期订单,1:是分期订单)
            if (toApp(request, appType)) {
                mv.setViewName("../app/payOrder");
            } else {
                mv.setViewName("payOrder");
            }
        }
        return mv;
    }
    /**
     *
     * 是否是App来源判断
     *
     * */
    private boolean toApp(HttpServletRequest request, Integer appType) {
        if (appType == null) { // 不带appType
            return false;
        }
        if (appType == 999 || appType == 998) { // web | wap
            return false;
        }
        // android 系统 走app订单支付页
        String os = request.getParameter("os");
        if (os != null) {
            // android 系统  | ios操作系统 appType = 1,6,7 走app订单支付页
            if (os.equals("2") || (os.equals("1") && (appType == 1 || appType == 6 || appType == 7))) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }


    //订单分批  路由
    @GetMapping(value = "/order/batch")
    public ModelAndView orderBatch(String payNum, @SessionAttribute Users user) {
        // 配置分批订单支付相关参数
        return orderService.handleMultiPayOrder(payNum, Constant.PAY_TYPE_BATCH, user);
    }

    // 订单转为百度有钱花-分2批
    @GetMapping(value = "/order/bd")
    public ModelAndView orderBaiduUMoney(String payNum, @SessionAttribute Users user) {
        return orderService.handleMultiPayOrder(payNum, Constant.PAY_TYPE_UMONEY, user);
    }

    // 订单转为咖啡易融-分2批
    @GetMapping(value = "/order/ce")
    public ModelAndView orderCoffeeEase(String payNum, @SessionAttribute Users user) {
        return orderService.handleMultiPayOrder(payNum, Constant.PAY_TYPE_COFFEE, user);
    }

    // 订单转为海米-分2批
    @GetMapping(value = "/order/hm")
    public ModelAndView orderHaiMi(String payNum, @SessionAttribute Users user) {
        return orderService.handleMultiPayOrder(payNum, Constant.PAY_TYPE_HAIMI, user);
    }

    //微信支付页面路由
    @GetMapping("/pay/wx")
    public ModelAndView payWx(@Value("${pay.domain}") String apiBasePath, String payNum, @SessionAttribute Users user) {
        ModelAndView mv = new ModelAndView();
        PayOrder order = orderService.findBy("payNum", payNum);
        if (!Objects.equals(user.getId(), order.getUserId())) {
            mv.setViewName("payNoOrder");
            return mv;
        }
        String apiUrl = Constants.HTTP + apiBasePath + "/pay/wx";
        HttpRequest request = HttpRequest
                .post(apiUrl)
                .send("payNum=" + payNum);
        int code = request.code();
        if (code == 200) {
            String body = request.body();
            JSONObject result = JSON.parseObject(body);
            if (result.getIntValue("code") == 200) {
                JSONObject data = result.getJSONObject("data");
                String codeUrl = data.getString("code_url");//支付二维码URL
                mv.addObject("qrcode", "<img width=\"300px\" height=\"300px\" src=\"data:image/png;base64, "
                        + Base64.encode(QRCode.from(codeUrl).to(ImageType.PNG).withSize(500, 500).stream().toByteArray())
                        + "\"/>");
                mv.addObject("timeout", data.getIntValue("timeout"));//二维码超时时间（分钟）
                mv.addObject("price", data.getDoubleValue("price"));//支付金额
                mv.addObject("payNum", payNum);//订单编号
                mv.addObject("sku", order.getCategoryId());
                mv.setViewName("payWx");
            } else {
                throw new RemoteInvokeException("微信支付API调用失败，错误信息：" + result.getString("message"));
            }
        } else {
            throw new RemoteInvokeException("微信支付API调用失败，URL：" + apiUrl);
        }
        return mv;
    }

    //语音支付页面路由
    @GetMapping("/pay/yy")
    public ModelAndView payYY(String payNum, @SessionAttribute Users user) {
        ModelAndView mv = new ModelAndView();
        PayOrder order = orderService.findBy("payNum", payNum);
        if (!Objects.equals(user.getId(), order.getUserId())) {
            mv.setViewName("payNoOrder");
            return mv;
        }
        mv.addObject("sku", order.getCategoryId());
        mv.addObject("payNum", order.getPayNum());//订单编号
        mv.addObject("price", order.getCostPrice());//商品价格
        mv.addObject("name", order.getProgramName());//商品名称
        mv.setViewName("payYy");
        return mv;
    }

    /**
     * 支付成功页面路由
     * */
    @GetMapping("/pay/success")
    public ModelAndView paySuccess(HttpServletRequest request, String orderNum, @RequestParam(defaultValue = "0") Integer appType, @SessionAttribute Users user) {
        ModelAndView mv;
        try {
            mv = buildPaySuccessModel(orderNum, user);
        } catch (Exception e) {
            mv = buildPaySuccessModel(orderNum, user);
        }
        setRequestParam(request, mv);

        return mv;
    }

    private ModelAndView buildPaySuccessModel(String orderNum, Users user) {
        ModelAndView mv = new ModelAndView();
        PayOrderTrans trans = transService.findBy("payNum", orderNum);
        PayOrder order = orderService.findById(trans.getOrderId());
        if (!Objects.equals(user.getId(), order.getUserId())) {
            mv.setViewName("payNoOrder");
            return mv;
        }
        boolean paid = true;
        String payType = order.getPayType();
        Long orderParentId = order.getOrderParentId();
        if (!Objects.equals(order.getOrderParentId(), Long.valueOf(0))) {
            order = orderService.findById(order.getOrderParentId());
            payType = order.getPayType();
            Condition condition = new Condition(PayOrder.class);
            condition.createCriteria().andEqualTo("orderParentId", orderParentId);
            List<PayOrder> subOrderList = orderService.findByCondition(condition);
            logger.info(JSONObject.toJSONString(subOrderList).toString());
            for (PayOrder subOrder : subOrderList) {
                if (!StringUtils.equals(PayStatus.PAY_STATUS_SUCCESS.getState(), subOrder.getPayStatus())) {
                    paid = false;
                    break;
                }
            }
        }
        mv.addObject("paid", paid);
        mv.addObject("payType", payType); // 支付类型
        mv.addObject("sku", order.getCategoryId());
        mv.addObject("payNum", order.getPayNum());//订单编号
        mv.addObject("price", trans.getPrice());//支付金额
        mv.addObject("name", order.getProgramName());//商品名称
        mv.addObject("payTime", DateFormatUtils.format(trans.getTransDate(), "yyyy-MM-dd HH:mm:ss"));//商品名称
        mv.addObject("address", payOrderMailingAddressService.findById(order.getId()));//寄送地址信息
        mv.addObject("sku", order.getCategoryId());//SKU
        mv.addObject(DNameConstants.DUIA_DOMAIN, duiaDomain);
        if (order.getType().equals("x")) {
            ClassRebuild classRebuild = classRebuildService.findByOrderId(order.getId());
            if (classRebuild != null) {
                mv.addObject("classId", classRebuild.getNewClassId());//班级ID
            }
        } else {
            Long classId = classStudentService.findCSByOrderId(order.getId());
            if (classId != null) {
                mv.addObject("classId", classId);//班级ID
            }
        }
        mv.setViewName("paySuccess");
        return mv;
    }

    //支付失败页面路由
    @GetMapping("/pay/fail")
    public ModelAndView payFail(HttpServletRequest request, String payNum, @SessionAttribute Users user) {
        ModelAndView mv = new ModelAndView();
        PayOrder order = orderService.findBy("payNum", payNum);
        if (!Objects.equals(user.getId(), order.getUserId())) {
            mv.setViewName("payNoOrder");
            return mv;
        }
        mv.addObject("sku", order.getCategoryId());
        mv.addObject("payNum", order.getPayNum());//订单编号
        mv.addObject("price", order.getCostPrice());//商品价格
        mv.addObject("name", order.getProgramName());//商品名称
        mv.addObject("payTime", order.getPayTime());//商品名称
        mv.addObject("address", payOrderMailingAddressService.findById(order.getId()));//寄送地址信息
        mv.addObject("sku", order.getCategoryId());//寄送地址信息
        mv.addObject(DNameConstants.DUIA_DOMAIN, duiaDomain);
        mv.setViewName("payDefeated");
        setRequestParam(request, mv);
        return mv;
    }

    /*从请求中获取数据*/
    public void setRequestParam(HttpServletRequest request, ModelAndView mv) {
        Enumeration<String> paramNames = request.getParameterNames();
        if (paramNames != null) {
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(request.getParameter(name))) {
                    mv.addObject(name, request.getParameter(name));
                }
            }
        }
    }

    // 订单创建成功后的跳转
    private void createOrderSetViewName(ModelAndView mv, PayOrder order, Integer appType, Integer os) {
        // 零元购订单
        Boolean zeroPaySuccess = false; // 零元购支付成功状态
        if (Objects.equals(Constant.PAY_TYPE_ZEROPAY, order.getPayType())) {
            // 调用零元购支付接口，再重定向到支付成功页面
            // 查询支付编号 循环查询
            String orderNum = null;
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PayOrder payOrder = this.orderService.findById(order.getId());

                if (null == payOrder) {
                    break;
                }
                orderNum = payOrder.getOrderNum();
                if (null != orderNum) {
                    break;
                }
            }

            mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/pay/success?orderNum=" + orderNum + (appType != null ? "&appType=" + appType : ""));
            zeroPaySuccess = true;
        }
        if (!zeroPaySuccess) {
            //防止重复刷新URL创建订单，创建订单后进行重定向
            if (appType != null) {
                mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/order/payment?num=" + order.getPayNum() + "&appType=" + appType + "&os=" + os);
            } else {
                mv.setViewName("redirect:" + Constants.HTTPS + itemDomain + "/order/payment?num=" + order.getPayNum() + "&os=" + os);
            }
        }
    }

    /**
     * 邮寄地址
     */
    @GetMapping("/pay/site")
    public String paySite() {
        return "addAddress";
    }

    /**
     * 优惠券
     */
    @GetMapping("/pay/couponRoute")
    public String payCoupon() {
        return "addCoupon";
    }

    @GetMapping("/pay/contact")
    public String payContact() {
        return "payContact";
    }

    @GetMapping("/head/{sku}")
    public ModelAndView head(@PathVariable Integer sku) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("sku", sku);
        mv.setViewName("staticHead");
        return mv;
    }

    @GetMapping("/foot")
    public String foot() {
        return "staticFoot";
    }

    @GetMapping("/toolbar")
    public String toolbar() {
        return "staticToolbar";
    }

    @GetMapping("/pay/ebank")
    public String payEBank() {
        return "payEBank";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/insure")
    public ModelAndView insure(Long insureId) {
        ModelAndView mv = new ModelAndView();
        if (insureId != null) {
            mv.addObject("content", aggrementTemplateService.findById(insureId).getContent());
        }
        mv.setViewName("insure");
        return mv;
    }

    /**
     * 保险信息页面：ios使用
     *
     * @param templateId
     * @return
     */
    @GetMapping("/payInsure")
    public ModelAndView payInsure(Long templateId) {
        ModelAndView mv = new ModelAndView();
        if (templateId != null) {
            mv.addObject("content", aggrementTemplateService.findById(templateId).getContent());
        }
        mv.setViewName("payInsure");
        return mv;
    }

    //确认订单  路由
    @GetMapping("/order/confirmRoute")
    public String orderConfirm() {
        return "payConfirm";
    }

    //升级-确认订单  路由
    @GetMapping("/order/upgrade/confirmRoute")
    public String orderUpgradeConfirm() {
        return "payUpgradeConfirm";
    }

    /**
     * @param * @param null
     * @Author: Hero
     * @Description: 微信公众号跳转路由，通过拦截器判断openid是否为空，如果为空则重新查询openid，获取openid
     * @Date: Created in 13:51 2018/5/14
     */
    @RequestMapping(value = "/wap/wxCount")
    public ModelAndView wxCount(String openid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("openid", openid);
        mv.setViewName("wxCount");
        return mv;
    }

    /**
     * @param * @param null
     * @Author: Hero
     * @Description: 路由跳转到wxSku 页面
     * @Date: Created in 10:41 2018/5/15
     */
    @GetMapping("/wap/wxSku")
    public String wxSku() {
        return "wxSku";
    }

    /**
     * 跳转到微信app页面
     *
     * @return
     */
    @GetMapping("/wap/wxApp")
    public String wxApp() {

        return "wxApp";
    }

    /**
     * app支付订单
     */
    @GetMapping("/app/paymentOrder")
    public String paymentOrder(HttpServletRequest request, Integer appType) {
        if (toApp(request, appType)) {
            return "../app/payOrder";
        } else {
            return "payOrder";
        }
    }

    /**
     * wap支付订单
     */
    @GetMapping("/wap/payOrderWap")
    public String payOrderWap(HttpServletRequest request, Integer appType) {
        if (toApp(request, appType)) {
            return "../app/payOrder";
        } else {
            return "payOrder";
        }
    }

    /**
     * 微信支付订单
     */
    @GetMapping("/order/payOrderWX")
    public String payOrderWX() {
        return "payOrder_wx";
    }

    /**
     * 支付失败
     */
    @GetMapping("/payError")
    public String payError() {
        return "payError";
    }

    /**
     * 公众号绑定链接
     *
     * @return
     */
    @Deprecated
    @GetMapping("/wap/wxCet")
    public void wxCet(HttpServletResponse response) throws IOException {
        String url = Constants.HTTPS + itemDomain + "/activity/wap/wxCet?appType=0";
        logger.info("================================" + url);
        response.sendRedirect(url);
    }


    /**
     * =====================================图书订单=====================================
     * */
    /**
     * 图书订单-确认订单路由
     * */
    @GetMapping("/bookOrder/confirmRoute")
    public String bookOrderConfirmRoute() {
        return "bookConfirmOrder";
    }

    /**
     * 图书订单-邮寄地址路由
     * */
    @GetMapping("/bookOrder/addressRoute")
    public String bookOrderAddressRoute() {
        return "";
    }

    /**
     * 图书订单-去结算路由
     * */
    @GetMapping("/bookOrder/paymentRoute")
    public String bookOrderPaymentRoute() {
        return "bookPayConfirm";
    }

    /**
     * 图书订单-支付成功
     * */
    @GetMapping("/bookOrder/paySuccess")
    public String bookOrderPaySuccessRoute() {
        return "bookPaySuccess";
    }

    /**
     * 图书订单-支付失败
     * */
    @GetMapping("/bookOrder/payError")
    public String bookOrderPayErrorRoute() {
        return "bookPayFail";
    }

    /**
     * 随身学领取微信
     */
    @GetMapping("/wap/bookApp")
    public String SsxWeChatReceive(){
        return "bookApp";
    }


    /**
     * =====================================图书订单=====================================
     * */
}
