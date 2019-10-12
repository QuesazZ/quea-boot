package com.quesa.mybootproject.common.exception;

/**
 * 
 */
public class RunException extends RuntimeException {
    private static final long serialVersionUID = 8639609611794399340L;

    /**
     * @param message
     */
    public RunException(String message) {
        super(message);
    }

    public RunException(Throwable throwable) {
        super(throwable);
    }

    public RunException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
