package com.cboy.connection.client.codec;

import com.cboy.common.pojo.NeneMsg;
import com.cboy.common.utils.JsonUtils;
import com.cboy.connection.codec.NeneDecoder;
import com.cboy.connection.codec.NeneEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class NeneEncoderTest {

    EmbeddedChannel channel;

    @BeforeEach
    void setUp() {
        channel = new EmbeddedChannel(new NeneEncoder());
    }

    @AfterEach
    void tearDown() {
        channel.finish();
    }

    @Test
    void testEncode() {
        NeneMsg msg = new NeneMsg();
        msg.setMsgId(System.currentTimeMillis()+"");
        boolean result = channel.writeOutbound(msg);
        Assertions.assertTrue(result);
        ByteBuf written = readAll(channel);
        byte[] actual = new byte[written.readableBytes()];
        written.readBytes(actual);
        String actualStr = new String(actual, StandardCharsets.UTF_8);
        // except
        String json = JsonUtils.toJson(msg);
        byte[] byteArr = json.getBytes(StandardCharsets.UTF_8);

        Assertions.assertArrayEquals(byteArr, actual);
    }


    private static ByteBuf readAll(EmbeddedChannel channel) {
        ByteBuf buf = Unpooled.buffer();
        ByteBuf read;
        while ((read = channel.readOutbound()) != null) {
            buf.writeBytes(read);
            read.release();
        }
        return buf;
    }
}
