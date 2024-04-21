package com.cboy.connection.config;

import com.cboy.common.pojo.NeneMsg;
import com.cboy.connection.server.DefaultTcpServer;
import com.cboy.connection.server.TcpServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializer {

    @Bean
    public TcpServer<NeneMsg> tcpServer() {
        DefaultTcpServer tcpServer = new DefaultTcpServer();
        tcpServer.start();
        return tcpServer;
    }


}
