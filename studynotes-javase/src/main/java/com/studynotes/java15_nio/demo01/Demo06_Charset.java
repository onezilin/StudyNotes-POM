package com.studynotes.java15_nio.demo01;


import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.Map;
import java.util.Set;

/**
 * Description: Charset 字符集表示字符及其对应的二进制编码，不同字符集中相同字符的二进制编码可能不同
 */
public class Demo06_Charset {
    // 输出支持的字符集
    @Test
    void getAllCharst() {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    Charset charset = Charset.forName("GBK");

    String string = "啦啦哈哈吧吧";

    // 字符集获取解码器和编码器
    @Test
    void enCode() throws Exception {
        // 获取编码器
        CharsetEncoder encoder = charset.newEncoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put(string);
        charBuffer.flip();

        // 可以看出两种不同编码格式生成的byte的区别
        System.out.println(string.getBytes(StandardCharsets.UTF_8)[0]);

        // 将char编码成对应格式byte
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());//-64-78-64-78-71-2-7-2-80-55-80-55
        }

        decode(byteBuffer);
    }

    void decode(ByteBuffer byteBuffer) throws CharacterCodingException {
        // 获取解码器
        CharsetDecoder decoder = charset.newDecoder();

        // 将对应格式字节解码字符
        byteBuffer.flip();
        CharBuffer cBuf2 = decoder.decode(byteBuffer);
        System.out.println(cBuf2.toString());// 啦啦哈哈吧吧
    }
}
