package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.LineNumberInputStream;

/**
 * Description: LineNumberInputStream 获取和设置行号
 */
public class Demo11_LineNumberInputStream {

    @Test
    void test() {
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile());
             LineNumberInputStream lineNumberInputStream = new LineNumberInputStream(inputStream)) {
            int readData;
            lineNumberInputStream.setLineNumber(0);
            while ((readData = lineNumberInputStream.read()) != -1) {
                System.out.println((char) readData);
                if (readData == '\n') {
                    System.out.println(lineNumberInputStream.getLineNumber());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
