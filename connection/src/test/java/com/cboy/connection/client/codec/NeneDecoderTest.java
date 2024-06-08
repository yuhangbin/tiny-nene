package com.cboy.connection.client.codec;

import com.cboy.common.pojo.NeneMsg;
import com.cboy.connection.codec.NeneDecoder;
import com.cboy.connection.codec.NeneEncoder;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NeneDecoderTest {

    private EmbeddedChannel channel;

    @BeforeEach
    void setUp() {
        channel = new EmbeddedChannel(new NeneEncoder(),
                new NeneDecoder());
    }

    @Test
    void testDecode() {
        // {"msgId":"8db1fe42-d886-4cc4-be0f-598b3866d8cc","msgType":0,"receiverIds":null,"token":null}
        NeneMsg msg = new NeneMsg();
        msg.setMsgId(System.currentTimeMillis() + "");

        Assertions.assertTrue(channel.writeInbound(msg));
        channel.flushOutbound();

        NeneMsg actual = channel.readInbound();

        Assertions.assertNotNull(actual);

        Assertions.assertEquals(msg.getMsgId(), actual.getMsgId());
    }
}
