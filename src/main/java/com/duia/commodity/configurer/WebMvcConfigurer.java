package com.duia.commodity.configurer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.duia.commodity.common.config.WxGZHPayConfig;
import com.duia.commodity.common.util.SpringUtils;
import com.duia.commodity.common.util.WebUtil;
import com.duia.constant.Constants;
import com.duia.constant.DNameConstants;
import com.duia.resultG.Result;
import com.duia.resultG.ResultCode;
import com.duia.commodity.core.ServiceException;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件
    @Value("${static.domain}")
    private String staticBasePath;//静态文件基础路径
    @Value("${tu.domain}")
    private String tuDomain;//图片服务器基础路径
    @Value("${pay.domain}")
    private String payBasePath;//图片服务器基础路径
    @Value("${duia.domain}")
    private String duiaBasePath;
    @Value("${tiku.domain}")
    private String tikuBasePath;
    @Value("${bang.domain}")
    private String bangBasePath;
    @Value("${tongji.domain}")
    private String tongjiDomain;
    @Value("${config.cross-origin}")
    private String CrossOrigin;
    @Value("${item.domain}")
    private String itemDomain;
    @Value("${common.domain}")
    private String commonStaticDomain;//公共静态资源路径
    @Value("${ai.domain}")
    private String statistics;//统计路径
    @Value("${sku.domain}")
    private String skuDomain;
    @Value("${uc.domain}")
    private String ucDomain;
    @Value("${sso.url}")
    private String ssoDomain;
    @Value("${mitem.domain}")
    private String mitem;
    @Value("${version}")
    private String staticVersion;
    @Value("${mbook.domain}")
    private String bookStaticBasePath;


    // 微信授权地址
    private static final String OAUTH2_AUTHORIZE_API = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";


    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero,//Number null -> 0
                SerializerFeature.DisableCircularReferenceDetect);// 循环引用->$ref
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                ModelAndView mv = new ModelAndView();
                Result result = new Result();
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    if (null != handlerMethod.getBean().getClass().getAnnotation(RestController.class)
                            || null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {//JSON视图
                        if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                            logger.info(e.getMessage());
                        } else if (e instanceof ServletException) {
                            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                        } else {
                            result.setCode(ResultCode.SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                            String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                    request.getRequestURI(),
                                    handlerMethod.getBean().getClass().getName(),
                                    handlerMethod.getMethod().getName(),
                                    e.getMessage());
                            logger.error(message, e);
                        }
                        responseResult(response, result);
                    } else {//普通页面
                        String viewName500 = WebUtil.isWap(request) ? "wap/error/500" : "web/error/500";
                        mv.setViewName(viewName500);
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    String viewName500 = WebUtil.isWap(request) ? "wap/error/500" : "web/error/500";
                    mv.setViewName(viewName500);
                    logger.error(e.getMessage(), e);
                }
                return mv;
            }
        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(CrossOrigin.split(","));
    }

    //添加拦截器
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                if ("get".equals(request.getMethod().toLowerCase()) && modelAndView != null) {
                    String viewName = modelAndView.getViewName();
                    if (!StringUtils.contains(viewName, "redirect")) {

                        modelAndView.addObject(DNameConstants.STATIC_DOMAIN, staticBasePath);
                        modelAndView.addObject(DNameConstants.TU_DOMAIN, tuDomain);
                        modelAndView.addObject(DNameConstants.PAY_DOMAIN, payBasePath);
                        modelAndView.addObject(DNameConstants.DUIA_DOMAIN, duiaBasePath);
                        modelAndView.addObject(DNameConstants.TI_KU_DOMAIN, tikuBasePath);
                        modelAndView.addObject(DNameConstants.BANG_DOMAIN, bangBasePath);
                        modelAndView.addObject(DNameConstants.TONG_JI_DOMAIN, tongjiDomain);
                        modelAndView.addObject(DNameConstants.COMMON_STATIC_DOMAIN, commonStaticDomain);
                        modelAndView.addObject(DNameConstants.AI_DOMAIN, statistics);
                        modelAndView.addObject(DNameConstants.SKU_DOMAIN, skuDomain);
                        modelAndView.addObject(DNameConstants.UC_DOMAIN, ucDomain);
                        modelAndView.addObject(DNameConstants.SSO_DOMAIN, ssoDomain);
                        modelAndView.addObject(DNameConstants.ITEM_DOMAIN, itemDomain);
                        modelAndView.addObject(DNameConstants.ITEM_STATIC_DOMAIN, mitem);
                        modelAndView.addObject(DNameConstants.BOOK_STATIC_DOMAIN, bookStaticBasePath);
                        modelAndView.addObject(DNameConstants.STATIC_VERSION, staticVersion);
                        if (WebUtil.isWap(request)) {
                            //获取访问的浏览器类型
                            String ua = request.getHeader("user-agent").toLowerCase();
                            String requestUrl = request.getRequestURI();
                            // 是微信浏览器
                            if (ua.indexOf("micromessenger") > 0 && ("/order/payment".equals(requestUrl) || "/order/payOrderWX".equals(requestUrl))) {
                                viewName = "app/payOrder_wx";
                            } else {
                                viewName = "wap/" + viewName;
                            }
                        } else {
                            viewName = "web/" + viewName;
                        }
                        modelAndView.setViewName(viewName);
                    }
                }

            }
        });

        // 微信公众号支付拦截器
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
                //获取访问的浏览器类型
                String ua = request.getHeader("user-agent").toLowerCase();

                // 判断是否：微信浏览器
                if (ua.indexOf("micromessenger") > 0) {
                    // 从request中获取payNum参数，据此查询订单中是否缓存openid
                    String num = request.getParameter("num");
                    PayOrderService orderService = SpringUtils.getBean(PayOrderService.class);
                    PayOrder payOrder = orderService.findBy("payNum", num);
                    if (payOrder.getOpenId() != null && !payOrder.getOpenId().equals("")) {
                        return true;
                    } else {
                        //重定向至登录页面
                        String url = OAUTH2_AUTHORIZE_API.replace("APPID", WxGZHPayConfig.APPID)
                                .replace("REDIRECT_URI", URLEncoder.encode(Constants.HTTP + itemDomain, "utf-8") + "%2fwx%2fgzh%2fauthorityNotify" + "?data=" + num)
                                .replace("SCOPE", "snsapi_base").replace("STATE", request.getRequestURI());
                        response.sendRedirect(url);
                        return false;
                    }
                }
                //不是微信浏览器
                return true;
            }

        }).addPathPatterns("/order/payment", "/order/payOrderWX");

        // 微信公众号获取openid拦截器
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
                //获取访问的浏览器类型
                String ua = request.getHeader("user-agent").toLowerCase();

                // 判断是否：微信浏览器
                if (ua.indexOf("micromessenger") > 0) {
                    // 从request中获取是否缓存openid
                    String openid = request.getParameter("openid");
                    if (openid != null && !openid.equals("")) {
                        return true;
                    } else {
                        //重定向至登录页面
                        String url = OAUTH2_AUTHORIZE_API.replace("APPID", WxGZHPayConfig.APPID)
                                .replace("REDIRECT_URI", URLEncoder.encode(Constants.HTTP + itemDomain, "utf-8") + "%2fwx%2fgzh%2fgetOpenid")
                                .replace("SCOPE", "snsapi_base").replace("STATE", request.getRequestURI());
                        response.sendRedirect(url);
                        return false;
                    }
                }
                //不是微信浏览器
                return true;
            }

        }).addPathPatterns("/wap/wxCount");
        super.addInterceptors(registry);
    }


    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrorPageRegistrar() {
            @Override
            public void registerErrorPages(ErrorPageRegistry registry) {
                registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            }
        };
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

}
