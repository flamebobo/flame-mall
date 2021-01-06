package com.flame.mall.admin.util;

import com.flame.mall.admin.service.OmsOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/30 11:03
 */
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Autowired
    private OmsOrderService omsOrderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyExpiredListener.class);

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        LOGGER.info("redis key 过期：pattern={},channel={},key={}", new String(pattern), channel, key);
        omsOrderService.closeOrder(key);
    }

}
