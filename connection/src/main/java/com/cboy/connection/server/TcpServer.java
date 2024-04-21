package com.cboy.connection.server;

public interface TcpServer<Msg> {

    /**
     * Start this server.
     */
    void start();

    void push(Msg resp);

    /**
     * Shutdown this server.
     */
    void shutdown();

}
