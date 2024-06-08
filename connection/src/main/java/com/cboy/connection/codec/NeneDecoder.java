package com.cboy.connection.codec;

import com.cboy.common.pojo.NeneMsg;
import com.cboy.common.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class NeneDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // check length part
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        byteBuf.markReaderIndex();
        int len = byteBuf.readInt();

        if (byteBuf.readableBytes() < len) {
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] data = new byte[len];
        byteBuf.readBytes(data);

        String json = new String(data, StandardCharsets.UTF_8);

        NeneMsg<?> msg = JsonUtils.fromJson(json, NeneMsg.class);

        list.add(msg);
    }
}
