package com.cboy.connection.server;

public interface TcpServer<Msg> {

    /**
     * Start this server.
     */
    void start();

    /**
     * Shutdown this server.
     */
    void shutdown();

}
