package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 * Description: 将 char 数组转化为字符输入流
 */
public class Demo20_CharArrayReader {

    @Test
    void test() {
        try (CharArrayReader charArrayReader = new CharArrayReader("0123456789中国龘\uD834\uDF06".toCharArray())) {

            int readData;
            while ((readData = charArrayReader.read()) != -1) {
                System.out.println((char) readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
