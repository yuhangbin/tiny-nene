package com.cboy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MsgTypeEnum {

    CLOSE(0),

    ;

    private final int msgType;
}
