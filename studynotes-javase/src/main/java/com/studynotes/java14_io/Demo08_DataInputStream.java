package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Description: DataInputStream readInt() 增强方法，返回 int 类型的数据
 */
public class Demo08_DataInputStream {

    @Test
    void test() {
        String a = "01100001"; // 'a' 二进制
        System.out.println(Integer.parseInt(a + a + a + a, 2));

        byte[] array = new byte[]{'a', 'a', 'a', 'a'};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(array);
             DataInputStream dataInputStream = new DataInputStream(inputStream);) {

            int readData = 0;
            while ((readData = dataInputStream.readInt()) != -1) {
                System.out.println(readData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
