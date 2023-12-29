package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Description: 将字符数据转化为字符输出流，写入 char 数组缓存中
 */
public class Demo24_CharArrayWriter {

    @Test
    void test() {
        try (FileWriter fileWriter = new FileWriter(CommonUtil.getFile());
             CharArrayWriter charArrayWriter = new CharArrayWriter()) {
            charArrayWriter.write("0123456789中国龘\uD834\uDF06");
            // 写入 fileWriter 中
            charArrayWriter.writeTo(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
