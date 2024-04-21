package com.cboy.connection.session;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionManager {

    private static final Map<String, Channel> userChannelMap = new ConcurrentHashMap<>(64);

    public static void save(String userId, Channel channel) {
        if (StringUtils.isNotEmpty(userId) && channel != null) {
            log.info("[SessionManager:save] userId:{} channel:{}", userId, channel.remoteAddress());
            userChannelMap.put(userId, channel);
        }
    }

    public static Optional<Channel> get(String userId) {
        return Optional.ofNullable(userChannelMap.getOrDefault(userId, null));
    }
}
