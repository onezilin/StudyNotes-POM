package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Description: PipedInputStream 和 PipedOutputStream 搭配使用，PipedInputStream 接收 PipedOutputStream 写入的数据，用于两个线程间的通信（字节数据传输）
 */
public class Demo14_PipedInputStream {

    // 在一个线程中查看阻塞的情况
    public static void main(String[] args) {
        try (PipedOutputStream out = new PipedOutputStream();
             // pipeSize 值改为 4，查看阻塞的情况
             PipedInputStream in = new PipedInputStream(out, 1000)) {

            out.write("hello kl".getBytes(StandardCharsets.UTF_8));

            int receive;
            while ((receive = in.read()) != -1) {
                System.err.print((char) receive);
            }

            System.out.println("完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test() {
        try (PipedOutputStream out = new PipedOutputStream();
             PipedInputStream in = new PipedInputStream(out, 4)) {
            new Thread(() -> {
                try {
                    int count = 0;
                    while (count < 100) {
                        // 写入数据
                        out.write("hello kl".getBytes(StandardCharsets.UTF_8));
                        count++;
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            int receive;
            // 读取数据
            while ((receive = in.read()) != -1) {
                System.err.print((char) receive);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
