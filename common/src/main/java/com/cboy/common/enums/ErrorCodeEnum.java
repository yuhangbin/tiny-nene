package com.cboy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    SUCCESS(0, "success"),
    ERROR(1, "error")

    ;

    private final int ec;
    private final String em;
}
