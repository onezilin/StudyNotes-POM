package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringBufferInputStream;

/**
 * Description: StringBufferInputStream 源头为 StringBuffer
 */
public class Demo06_StringBufferInputStream {

    @Test
    void test() {

        try (StringBufferInputStream inputStream = new StringBufferInputStream("1234567890");) {
            int readData;
            while ((readData = inputStream.read()) != -1) {
                System.out.println((char) readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
