package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

/**
 * Description: 将 String 转化为字符输入流，替代了 StringInputStream
 */
public class Demo21_StringReader {

    @Test
    void test() {
        try (StringReader reader = new StringReader("0123456789中国龘\uD834\uDF06");) {
            int readData;
            while ((readData = reader.read()) != -1) {
                System.out.println(readData);
            }

            System.out.println(Integer.toBinaryString(57094)); // 获取 DF06 的二进制编码值
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
