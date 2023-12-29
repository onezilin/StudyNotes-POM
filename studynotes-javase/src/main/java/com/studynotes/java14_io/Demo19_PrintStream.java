package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Description: PrintStream 可以写入其他类型的数据
 */
public class Demo19_PrintStream {

    @Test
    void test() {
        try (FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile());
             PrintStream printStream = new PrintStream(outputStream);) {

            printStream.print("I Love 中国");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
