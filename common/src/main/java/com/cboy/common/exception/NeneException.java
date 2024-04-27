package com.cboy.common.exception;

public class NeneException extends RuntimeException{

    public NeneException() {
        super();
    }

    public NeneException(String message) {
        super(message);
    }

    public NeneException(String message, Throwable cause) {
        super(message, cause);
    }
}
