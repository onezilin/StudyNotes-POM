package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description: BufferedOutputStream 先将数据写入缓存中，待缓存满时再写入终端
 */
public class Demo18_BufferedOutputStream {

    @Test
    void test() {
        try {
            FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            byte[] buf = "0123456789".getBytes();
            bufferedOutputStream.write(buf);

            // 需要刷新，才能在缓存未满时，将缓存中的数据写入终端
            bufferedOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
