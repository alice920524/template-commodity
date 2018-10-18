package com.duia.commodity.common.exception;

/**
 * Created by 李恒名 on 2017/7/20.
 */
public class RemoteInvokeException extends RuntimeException {
    public RemoteInvokeException() {
        super();
    }

    public RemoteInvokeException(String message) {
        super(message);
    }

    public RemoteInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteInvokeException(Throwable cause) {
        super(cause);
    }
}
