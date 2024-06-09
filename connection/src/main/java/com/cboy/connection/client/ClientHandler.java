package com.cboy.connection.client;

import com.cboy.common.enums.MsgTypeEnum;
import com.cboy.common.pojo.NeneMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<NeneMsg> {

    @Autowired
    TcpClient tcpClient;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NeneMsg msg) throws Exception {
        log.info("[ClientHandler:channelRead0] msg:{}", msg);
        if (MsgTypeEnum.CLOSE.getMsgType() == msg.getMsgType()) {
            tcpClient.shutdown();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
