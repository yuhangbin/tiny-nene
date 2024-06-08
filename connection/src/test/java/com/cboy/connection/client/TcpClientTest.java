package com.cboy.connection.client;

import org.junit.jupiter.api.Test;

public class TcpClientTest {

    @Test
    void testTcpClient() {

        TcpClient client = new TcpClient("localhost", 20000);
        client.start();
        client.shutdown();
    }
}
