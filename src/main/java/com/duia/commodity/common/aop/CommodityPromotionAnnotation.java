package com.duia.commodity.common.aop;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommodityPromotionAnnotation {

    String describe() default "分享购注解";
}
