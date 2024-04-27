package com.cboy.common.pojo;

import com.cboy.common.enums.ErrorCodeEnum;
import com.cboy.common.exception.NeneException;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeneResp<T> extends NeneMsg{
    private int ec;
    private String em;

    private T data;


    public static <T> NeneResp<T> error(String msgId, int msgType, int ec, String em, T data) {
        NeneResp<T> resp = new NeneResp<>();
        resp.setMsgId(msgId);
        resp.setMsgType(msgType);
        resp.setEc(ec);
        resp.setEm(em);
        resp.setData(data);
        return resp;
    }

    public static <T> NeneResp<T> error(NeneMsg neneMsg, NeneException e) {
        return error(neneMsg.getMsgId(), neneMsg.getMsgType(), e.getErrorCodeEnum().getEc(), e.getErrorCodeEnum().getEm(), null);
    }

    public static <T> NeneResp<T> error(NeneMsg neneMsg, ErrorCodeEnum e) {
        return error(neneMsg.getMsgId(), neneMsg.getMsgType(), e.getEc(), e.getEm(), null);
    }

    public static <T> NeneResp<T> error(NeneMsg neneMsg) {
        return error(neneMsg, ErrorCodeEnum.ERROR);
    }
}
