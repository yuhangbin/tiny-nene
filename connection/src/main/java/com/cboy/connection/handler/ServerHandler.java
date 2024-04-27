package com.cboy.connection.handler;

import com.cboy.common.exception.NeneException;
import com.cboy.common.pojo.NeneMsg;
import com.cboy.common.pojo.NeneResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<NeneMsg> {

    // TODO check use newInstance() or valueOf()
    private static final AttributeKey<NeneMsg> NENE_MSG_ATTRIBUTE_KEY = AttributeKey.valueOf("NENE_MSG_ATTRIBUTE_KEY");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NeneMsg msg) throws Exception {
        ctx.channel().attr(NENE_MSG_ATTRIBUTE_KEY).set(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[ServerHandler:exceptionCaught] e:{}", ExceptionUtils.getStackTrace(cause));
        NeneMsg neneMsg = ctx.channel().attr(NENE_MSG_ATTRIBUTE_KEY).get();
        if (cause instanceof NeneException neneException) {
            NeneResp<Object> neneResp = NeneResp.error(neneMsg, neneException);
            log.info("[ServerHandler:exceptionCaught] neneResp:{}", neneResp);
            ctx.writeAndFlush(neneResp);
        } else {
            ctx.writeAndFlush(NeneResp.error(neneMsg));
        }
    }
}
