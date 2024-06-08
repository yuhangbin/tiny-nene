package com.cboy.connection.client;


import com.cboy.common.pojo.NeneMsg;
import com.cboy.common.utils.JsonUtils;
import com.cboy.connection.codec.NeneDecoder;
import com.cboy.connection.codec.NeneEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.net.InetSocketAddress;
import java.util.UUID;

@Slf4j
public class TcpClient {

    private final String host;
    private final int port;

    private final EventLoopGroup executors = new NioEventLoopGroup();

    ChannelFuture channelFuture;

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(executors)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NeneDecoder());
                            ch.pipeline().addLast(new ClientHandler());
                            ch.pipeline().addLast(new NeneEncoder());
                        }
                    });
            channelFuture = bootstrap.connect().sync();
            NeneMsg<?> req = new NeneMsg<>();
            req.setMsgId(UUID.randomUUID().toString());
            channelFuture.channel().writeAndFlush(req);
            log.info("[TcpClient:start] req:{}", JsonUtils.toJson(req));
        } catch (Throwable e) {
            log.error("[TcpClient:start] e:{}", ExceptionUtils.getStackTrace(e));
        }

    }

    public void shutdown() {
        try {
            executors.shutdownGracefully().syncUninterruptibly();
        } catch (Throwable e) {
            log.error("[TcpClient:shutdown] e:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
