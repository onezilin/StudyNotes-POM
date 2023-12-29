package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description: 向文件中写入数据
 */
public class Demo16_FileOutputStream {


    @Test
    void test() {
        // append 为 true 时，表示追加到
        try (FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile(), true)) {
            byte[] buf = "1234567890".getBytes();
            for (byte writeData : buf) {
                outputStream.write(writeData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
