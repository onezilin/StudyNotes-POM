package com.studynotes.java15_nio.demo01;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description: 将 FileInputStream 转化为 FileChannel，进行 IO 操作
 */
public class Demo05_FileChannel {

    @Test
    public void test() {
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile());
             FileChannel inChannel = inputStream.getChannel();
             FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile("test1.txt"));
             FileChannel outChannel = outputStream.getChannel();) {

            // 通过 FileChannel 运输 ByteBuffer 的方式
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
