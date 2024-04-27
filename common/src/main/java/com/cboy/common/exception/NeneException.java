package com.cboy.common.exception;

import com.cboy.common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NeneException extends RuntimeException{

    private ErrorCodeEnum errorCodeEnum;

    public NeneException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeneException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}
