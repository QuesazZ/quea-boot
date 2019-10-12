package com.quesa.mybootproject.common.exception;

/**
 * 获取参数异常
 *
 */
public class ParamException extends RuntimeException {
    private static final long serialVersionUID = 8639609611794399340L;

    /**
     * @param message
     */
    public ParamException(String message) {
        super(message);
    }

    public ParamException(Throwable throwable) {
        super(throwable);
    }

    public ParamException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
