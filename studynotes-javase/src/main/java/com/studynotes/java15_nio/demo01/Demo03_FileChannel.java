package com.studynotes.java15_nio.demo01;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Description: 通过直接缓冲区进行 IO 操作，对直接缓冲区的操作也会写回源文件
 */
public class Demo03_FileChannel {

    @Test
    public void test() {
        // StandardOpenOption 表示当前 Channel 支持的操作
        try (FileChannel inChannel = FileChannel.open(Paths.get(CommonUtil.getFile().getPath()), StandardOpenOption.READ,
                StandardOpenOption.WRITE);
             FileChannel outChannel = FileChannel.open(Paths.get(CommonUtil.getFile("test1.txt").getPath()),
                     StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);) {

            /*
             * FileChannel.MapMode：
             *  READ_ONLY：只读；
             *  READ_WRITE：对 MappedByteBuffer 的更改会写回对应文件；
             *  PRIVATE：对内存的更改不会写回文件。
             *
             * position：表示从文件第几个字节开始读取
             */
            // inChannel.size() 表示当前文件内容的字节数
            MappedByteBuffer bi = inChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            MappedByteBuffer bo = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            byte[] byteArray = new byte[bi.remaining()];
            bi.get(byteArray);
            bo.put(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
