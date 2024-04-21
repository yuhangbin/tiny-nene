package com.cboy.connection.handler;

import com.cboy.common.pojo.NeneReq;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<NeneReq> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NeneReq neneReq) throws Exception {

    }


}
