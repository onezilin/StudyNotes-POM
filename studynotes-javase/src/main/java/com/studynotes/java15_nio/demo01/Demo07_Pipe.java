package com.studynotes.java15_nio.demo01;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * SinkChannel.write -》 SourceChannel.read
 * nio中管道是两个线程的单向数据连接（不像Channel是双向）
 * SinkChannel可以用来向管道中写入数据，SourceChannel从管道中读出数据
 */
public class Demo07_Pipe {
    public static void main(String[] args) throws Exception {
        // 获取管道
        Pipe pipe = Pipe.open();

        // 通过SinkChannel写入数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        sinkChannel.write(ByteBuffer.wrap("通过管道单向传输的数据".getBytes(StandardCharsets.UTF_8)));

        // 通过SourceChannel读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        sourceChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));

        sourceChannel.close();
        sinkChannel.close();
    }
}
