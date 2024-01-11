package com.studynotes.demo03_messageListener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;

/**
 * Description: 自定义消息监听器，需要在配置类中配置
 */
public class Demo01_MyMessageListener implements MessageListener {

    private final RedisTemplate<String, Object> redisTemplate;

    public Demo01_MyMessageListener(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(@NonNull Message message, byte[] bytes) {
        byte[] body = message.getBody();
        String msgBody = (String) redisTemplate.getValueSerializer().deserialize(body);

        System.out.println("channel: " + new String(message.getChannel()));
        System.out.println("msgBody: " + msgBody);
        System.out.println("bytes: " + new String(bytes));
    }
}
