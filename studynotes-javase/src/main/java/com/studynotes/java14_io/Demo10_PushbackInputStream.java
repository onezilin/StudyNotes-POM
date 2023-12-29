package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Description: PushbackInputStream 的 unread() 方法将数据回推到流开头
 */
public class Demo10_PushbackInputStream {
    @Test
    void test() {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("1234567890".getBytes());
             PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);) {
            int readData;
            while ((readData = pushbackInputStream.read()) != -1) {
                System.out.println((char) readData);
                if (readData == '5') {
                    pushbackInputStream.unread(readData);
                }
                readData = pushbackInputStream.read();
                System.out.println((char) readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
