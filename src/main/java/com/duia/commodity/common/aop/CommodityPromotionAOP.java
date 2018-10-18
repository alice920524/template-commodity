package com.duia.commodity.common.aop;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.util.WebUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class CommodityPromotionAOP {

    @Pointcut("@annotation(com.duia.commodity.common.aop.CommodityPromotionAnnotation)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint p) throws Throwable {

        Object[] objects = p.getArgs();

        int appTypeParamIndex = getAppTypeParamIndex(this.getMethodParams(p.getTarget().getClass(), p.getSignature().getName()));

        if (appTypeParamIndex > -1) {
            this.setAppTypeValue(appTypeParamIndex, objects);
        }

        return p.proceed(objects);
    }

    // 更新appType值
    private Object setAppTypeValue(int appTypeParamIndex, Object[] objects) {
        // 下标异常
        if (appTypeParamIndex > objects.length) {
            return objects;
        }

        /**
         * 纠正web wap appType值
         * */
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();

        boolean isWap = WebUtil.isWap(request);

        Object appTypeObj = objects[appTypeParamIndex];

        if (null == appTypeObj) {
            if (isWap) {
                objects[appTypeParamIndex] = Constant.WAP_TYPE;
                return objects;
            } else {
                objects[appTypeParamIndex] = Constant.WEB_TYPE;
                return objects;
            }
        }

        Integer appType = (Integer) appTypeObj;

        // 纠正appType值
        if (Constant.WEB_TYPE.equals(appType) || Constant.WAP_TYPE.equals(appType)) {
            if (isWap) {
                objects[appTypeParamIndex] = Constant.WAP_TYPE;
            } else {
                objects[appTypeParamIndex] = Constant.WEB_TYPE;
            }
        }

        return objects;
    }

    // 获取appType下标
    private int getAppTypeParamIndex(String[] params) {
        if (null == params || params.length < 1) {
            return -1;
        }
        for (int i = 0; i < params.length; i++) {
            if ("appType".equals(params[i])) {
                return i;
            }
        }
        return -1;
    }

    // 获取某类某方法的方法参数
    private String[] getMethodParams(Class clazz, String methodName) {
        LocalVariableTableParameterNameDiscoverer localVar = new LocalVariableTableParameterNameDiscoverer();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return localVar.getParameterNames(method);
            }
        }

        return new String[]{};
    }

}
