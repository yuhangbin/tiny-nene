package com.cboy.connection.handler;

import com.cboy.common.pojo.NeneReq;
import com.cboy.common.pojo.NeneResp;

public interface HandlerManager {


    <T> NeneResp<T> handle(NeneReq<T> req);
}
