package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

/**
 * Description: 在Java 7中，try-with-resources 语句被引入，会自动调用 close()方法，以确保任何资源在语句结束时都被关闭。
 * 注意：能使用 try-with-resources 语句的资源必须实现 AutoCloseable 接口，该接口包含一个名为 close() 的方法。
 */
public class Demo14_TryWithResource {

    /**
     * Description: 以前的写法极其冗余繁杂
     */
    @Test
    void test1() {
        FileInputStream in = null;
        try {
            in = new FileInputStream("test.txt");
            int contents = in.read();
            // Process contents
        } catch (Exception e) {
            // Handle the error
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e) {
                // Handle the error
            }
        }
    }

    /**
     * Description: 现在的写法简洁明了
     */
    @Test
    void test2() {
        try (FileInputStream in = new FileInputStream("test.txt");
             FileInputStream in2 = new FileInputStream("test2.txt")) {
            int contents = in.read();
            // Process contents
        } catch (Exception e) {
            // Handle the error
        }
    }
}
