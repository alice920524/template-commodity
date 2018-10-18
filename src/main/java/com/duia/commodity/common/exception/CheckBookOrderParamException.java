package com.duia.commodity.common.exception;

/**
 * 校验订单自定义异常
 */
public class CheckBookOrderParamException extends RuntimeException {
    public CheckBookOrderParamException() {
        super();
    }

    public CheckBookOrderParamException(String message) {
        super(message);
    }

    public CheckBookOrderParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckBookOrderParamException(Throwable cause) {
        super(cause);
    }
}
