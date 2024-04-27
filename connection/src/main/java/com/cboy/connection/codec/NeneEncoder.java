package com.cboy.connection.codec;

import com.cboy.common.pojo.NeneMsg;
import com.cboy.common.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class NeneEncoder extends MessageToByteEncoder<NeneMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NeneMsg msg, ByteBuf out) throws Exception {
        // send two part of data to server
        String json = JsonUtils.toJson(msg);
        byte[] byteArr = json.getBytes(StandardCharsets.UTF_8);
        // first part: data length
        out.writeInt(byteArr.length);
        // second part: data
        out.writeBytes(byteArr);
    }

}
