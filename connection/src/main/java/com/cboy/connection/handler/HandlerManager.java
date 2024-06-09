package com.cboy.connection.handler;

import com.cboy.common.pojo.NeneMsg;
import org.springframework.stereotype.Component;

@Component
public class HandlerManager {

     public <T> NeneMsg<T> handle(NeneMsg<?> req) {
         NeneMsg<T> resp = new NeneMsg<>();
         resp.setMsgId(req.getMsgId());
         return resp;
     }
}
