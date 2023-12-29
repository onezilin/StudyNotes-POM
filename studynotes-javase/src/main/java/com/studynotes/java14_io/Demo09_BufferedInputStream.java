package com.studynotes.java14_io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Description: BufferedInputStream 缓存输入流中的 mark() 方法的不同点
 */
public class Demo09_BufferedInputStream {

    public static void main(String[] args) throws Exception {
        int size = 4;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("1234567890".getBytes());
             BufferedInputStream bis = new BufferedInputStream(inputStream, size)) {
            System.out.println("Char : " + (char) bis.read());

            System.out.println("-------mark(3)----------");
            // 标记当前读取的位置 2，最多可以再读取 readlimit 字节的数据
            bis.mark(3);
            System.out.println("Char : " + (char) bis.read());
            System.out.println("Char : " + (char) bis.read());
            System.out.println("Char : " + (char) bis.read());
            // 若读取超出 Math.max(readlimit, size) 字节，则 mark 标记失效，调用 reset 时抛异常
            System.out.println("Char : " + (char) bis.read());
            // System.out.println("Char : " + (char) bis.read());

            System.out.println("-------reset()----------");
            bis.reset();
            int b;
            while ((b = bis.read()) != -1) {
                System.out.println("char : " + (char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
