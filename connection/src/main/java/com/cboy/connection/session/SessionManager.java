package com.cboy.connection.session;

import com.cboy.common.pojo.NeneMsg;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionManager {

    private static final Map<Long, Channel> userChannelMap = new ConcurrentHashMap<>(64);

    public static void save(Long userId, Channel channel) {
        if (channel != null) {
            log.info("[SessionManager:save] userId:{} channel:{}", userId, channel.remoteAddress());
            userChannelMap.put(userId, channel);
        }
    }

    public static Optional<Channel> get(Long userId) {
        return Optional.ofNullable(userChannelMap.getOrDefault(userId, null));
    }

    public Optional<Channel> get(NeneMsg<?> req) {
        Optional<Long> userId = getUserId(req);
        if (userId.isPresent()) {
            return get(userId.get());
        }
        return Optional.empty();
    }

    public Optional<Long> getUserId(NeneMsg<?> neneMsg) {
        return getUserIdByToken(neneMsg.getToken());
    }

    public Optional<Long> getUserIdByToken(String token) {
        if (token == null) {
            return Optional.empty();
        }
        // TODO
        return Optional.empty();
    }
}
