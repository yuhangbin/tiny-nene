package com.cboy.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NeneException extends RuntimeException{

    private int ec;
    private String em;

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
