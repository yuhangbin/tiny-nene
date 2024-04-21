package com.cboy.connection.server;

import com.cboy.common.pojo.NeneMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

@Slf4j
public class DefaultTcpServer implements TcpServer<NeneMsg>, DisposableBean {


    @Override
    public void start() {

    }

    @Override
    public void push(NeneMsg neneResp) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void destroy() throws Exception {
        shutdown();
        log.info("[DefaultTcpServer:destroy] shutdown");
    }
}
