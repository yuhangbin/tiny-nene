package com.cboy.connection.server.handler;

import com.cboy.common.pojo.NeneMsg;
import com.cboy.connection.session.SessionManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<NeneMsg> {

    @Autowired
    SessionManager sessionManager;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NeneMsg msg) throws Exception {
        if (authentication(msg)) {
            ctx.pipeline().remove(this);
        }
    }

    private boolean authentication(NeneMsg neneMsg) {
        //TODO
        Optional<Long> userId = sessionManager.getUserId(neneMsg);
        return userId.isPresent();
    }
}
