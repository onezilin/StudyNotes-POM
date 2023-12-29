package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Description: ByteArrayInputStream 源头为字节数组
 */
public class Demo05_ByteArrayInputStream {

    @Test
    void test() {
        byte[] buffer = new byte[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9};

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer)) {
            int readData = 0;
            while ((readData = inputStream.read()) != -1) {
                System.out.println((byte) readData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
