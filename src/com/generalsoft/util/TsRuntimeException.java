package com.generalsoft.util;

/**
 * Created by IntelliJ IDEA.
 * User: wy
 * Date: 13-5-8
 * Time: 下午1:17
 */
public class TsRuntimeException extends RuntimeException {
    public TsRuntimeException() {
    }

    public TsRuntimeException(String message) {
        super(message);
    }

    public TsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TsRuntimeException(Throwable cause) {
        super(cause);
    }

}
