package com.cboy.connection.server;

import com.cboy.common.exception.NeneException;
import com.cboy.common.pojo.NeneMsg;
import com.cboy.connection.codec.NeneDecoder;
import com.cboy.connection.codec.NeneEncoder;
import com.cboy.connection.server.handler.LoginHandler;
import com.cboy.connection.server.handler.ServerHandler;
import com.cboy.connection.session.SessionManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class DefaultTcpServer implements TcpServer<NeneMsg<?>>, DisposableBean {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    private final EventLoopGroup workerGroup = new NioEventLoopGroup(10);

    public static final int PORT = 20000;

    @Autowired
    LoginHandler loginHandler;

    @Autowired
    SessionManager sessionManager;

    ChannelFuture channelFuture;

    @PostConstruct
    public void init() {
        new Thread(() -> {
            log.info("[DefaultTcpServer:init] server port:{}", PORT);
            start();
            log.info("[DefaultTcpServer:init] server started port:{}", PORT);
        }).start();
    }

    @Override
    public void start() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NeneDecoder());
//                            ch.pipeline().addLast(loginHandler);
                            ch.pipeline().addLast(new ServerHandler());
                            ch.pipeline().addLast(new NeneEncoder());
                        }
                    });
            channelFuture = serverBootstrap.bind().sync();
        } catch (Throwable e) {
            throw new NeneException("server init failed.", e);
        }

    }

    @Override
    public void shutdown() {
        try {
            channelFuture.channel().closeFuture().sync();
            bossGroup.shutdownGracefully().syncUninterruptibly();
            workerGroup.shutdownGracefully().syncUninterruptibly();
        } catch (Throwable e) {
            log.warn("[DefaultTcpServer:shutdown] e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void destroy() throws Exception {
        shutdown();
        log.info("[DefaultTcpServer:destroy] shutdown");
    }
}
