package com.cboy.connection.server.handler;

import com.cboy.common.enums.MsgTypeEnum;
import com.cboy.common.exception.NeneException;
import com.cboy.common.pojo.NeneMsg;
import com.cboy.common.utils.JsonUtils;
import com.cboy.connection.handler.HandlerManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<NeneMsg<Object>> {

    @Autowired
    HandlerManager handlerManager;

    // TODO check use newInstance() or valueOf()
    private static final AttributeKey<NeneMsg<Object>> NENE_MSG_ATTRIBUTE_KEY = AttributeKey.valueOf("NENE_MSG_ATTRIBUTE_KEY");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NeneMsg<Object> msg) throws Exception {
        log.info("[ServerHandler:channelRead0] msg:{}", JsonUtils.toJson(msg));
        ctx.channel().attr(NENE_MSG_ATTRIBUTE_KEY).set(msg);
        NeneMsg<?> neneResp = handlerManager.handle(msg);
        ctx.writeAndFlush(neneResp);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[ServerHandler:exceptionCaught] e:{}", ExceptionUtils.getStackTrace(cause));
        NeneMsg<?> req = ctx.channel().attr(NENE_MSG_ATTRIBUTE_KEY).get();
        if (cause instanceof NeneException neneException) {
            // TODO make nene msg create more easily
            NeneMsg<?> resp = new NeneMsg<>();
            resp.setMsgId(req.getMsgId());
            resp.setMsgType(MsgTypeEnum.CLOSE.getMsgType());
            resp.setEc(neneException.getErrorCodeEnum().getEc());
            resp.setEm(neneException.getErrorCodeEnum().getEm());
            log.info("[ServerHandler:exceptionCaught] neneResp:{}", resp);
            ctx.writeAndFlush(resp);
        } else {
            ctx.writeAndFlush(NeneMsg.error(req));
        }
    }
}
