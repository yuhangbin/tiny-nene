package com.cboy.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeneMsg<T> implements NeneProtocol{

    private String msgId;
    private Integer msgType;

    private T payload;

    private Integer ec;
    private String em;

    private String token;

    private List<Long> receiverIds;

    public static <T> NeneMsg<T> error(NeneMsg<?> msg) {
        NeneMsg<T> resp = new NeneMsg<>();
        resp.setMsgId(msg.getMsgId());
        resp.setMsgType(msg.getMsgType());
        return resp;
    }

}
