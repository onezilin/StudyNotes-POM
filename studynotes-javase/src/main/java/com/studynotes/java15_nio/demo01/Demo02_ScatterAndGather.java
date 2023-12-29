package com.studynotes.java15_nio.demo01;


import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 * Scatter：Read 分散读取，也就是将文件内容读取到不同的 Buffer 中,
 * Gather：Writer 聚集写出，也就是将不同的 Buffer 写到文件中
 */
public class Demo02_ScatterAndGather {

    // 分散读取
    @Test
    void ScatterRead() {
        try (RandomAccessFile raf = new RandomAccessFile(CommonUtil.getFile().getPath(), "rw");
             FileChannel fi = raf.getChannel();) {

            // 分配多个指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(4);
            ByteBuffer buf2 = ByteBuffer.allocate(8);
            ByteBuffer buf3 = ByteBuffer.allocate(1024);

            // 分散读取
            ByteBuffer[] bufs = {buf1, buf2, buf3};
            fi.read(bufs);

            for (ByteBuffer byteBuffer : bufs) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                System.out.println("--------------------");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 聚集写出
    @Test
    void GatherWriter() {
        try (RandomAccessFile raf = new RandomAccessFile(CommonUtil.getFile().getPath(), "rw");
             FileChannel fo = raf.getChannel();) {

            ByteBuffer buf1 = ByteBuffer.wrap("this is first Buffer \n".getBytes());
            ByteBuffer buf2 = ByteBuffer.wrap("this is second Buffer \n".getBytes());
            byte[] byteArray = "this is third Buffer \n".getBytes();
            ByteBuffer buf3 = ByteBuffer.allocate(byteArray.length);
            buf3.put(byteArray);
            buf3.flip();

            // 聚集写出
            ByteBuffer[] bufs = {buf1, buf2, buf3};
            fo.write(bufs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
