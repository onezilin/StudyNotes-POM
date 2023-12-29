package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Description: 将字符数据转化为字符输出流，写入 StringBuffer  中
 */
public class Demo25_StringWriter {

    @Test
    void test() {
        try (StringWriter stringWriter = new StringWriter()) {
            stringWriter.write("0123456789中国龘\uD834\uDF06");

            System.out.println(stringWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
