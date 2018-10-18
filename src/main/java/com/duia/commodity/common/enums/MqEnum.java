package com.duia.commodity.common.enums;

/**
 * Mq枚举类
 */
public enum MqEnum {

    // 后台订单开班队列
    NBACK_ORDER_OPEN_CLASSES("duia-nback-order", "class.auth");

    private String exchange;
    private String routingKey;

    MqEnum(String exchange, String routingKey) {
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
