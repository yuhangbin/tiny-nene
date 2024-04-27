package com.cboy.common.pojo;

import com.cboy.common.enums.ErrorCodeEnum;
import com.cboy.common.exception.NeneException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NeneReq<T> extends NeneMsg {

    private String token;

    @SuppressWarnings("unchecked")
    public static <T> NeneReq<T> getNeneReq(NeneMsg neneMsg) {
        if (neneMsg instanceof NeneReq<?> neneReq) {
            return (NeneReq<T>) neneReq;
        }
        throw new NeneException(ErrorCodeEnum.ERROR);
    }
}
