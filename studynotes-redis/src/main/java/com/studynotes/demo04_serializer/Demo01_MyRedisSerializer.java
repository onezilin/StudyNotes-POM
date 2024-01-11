package com.studynotes.demo04_serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Description: 自动 Redis 序列化器，可以对 Redis 的 key 和 value 进行序列化和反序列化
 * <p>
 * 测试步骤：
 * 1、将 RedisTemplate 的 value 序列化器设置为 Demo01_MyRedisSerializer
 */
public class Demo01_MyRedisSerializer implements RedisSerializer<Object> {

    private final Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    private final Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        if (obj == null) {
            return EMPTY_BYTE_ARRAY;
        }
        return this.serializingConverter.convert(obj);
    }

    @Override
    public Object deserialize(byte[] data) throws SerializationException {
        if (data == null || data.length == 0) {
            return null;
        }
        return this.deserializingConverter.convert(data);
    }
}
