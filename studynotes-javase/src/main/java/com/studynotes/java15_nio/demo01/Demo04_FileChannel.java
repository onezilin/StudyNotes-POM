package com.studynotes.java15_nio.demo01;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Description: 通过零拷贝的方式传输文件，不可以对文件进行更改，传输速度最快
 */
public class Demo04_FileChannel {

    @Test
    public void test() {
        try (FileChannel inChannel = FileChannel.open(Paths.get(CommonUtil.getFile().getPath()), StandardOpenOption.READ,
                StandardOpenOption.WRITE);
             FileChannel outChannel = FileChannel.open(Paths.get(CommonUtil.getFile("test1.txt").getPath()),
                     StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);) {

            inChannel.transferTo(0, inChannel.size(), outChannel);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
