package com.generalsoft.util;

/**
 * Created by IntelliJ IDEA.
 * User: wy
 * Date: 12-6-27
 * Time: 上午9:06
 *
 * Ts抛出的各种异常
 */
public class TsException extends Exception {

    public TsException() {
    }

    public TsException(String message) {
        super(message);
    }

    public TsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TsException(Throwable cause) {
        super(cause);
    }

}
