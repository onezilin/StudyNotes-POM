package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

/**
 * Description: PrintWriter 的 print 方法
 */
public class Demo28_PrintWriter {

    @Test
    void test() {
        System.out.println((byte) 255);
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.println("new 数据");
        printWriter.flush();
    }
}
