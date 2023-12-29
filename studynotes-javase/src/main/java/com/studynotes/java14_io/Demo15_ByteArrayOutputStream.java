package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description: 向 ByteArrayOutputStream 写入字节数据
 */
public class Demo15_ByteArrayOutputStream {

    @Test
    void test() {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(CommonUtil.getFile())) {
            byte[] buf = "1234567890".getBytes();
            for (byte writeData : buf) {
                outputStream.write(writeData);
            }
            outputStream.write(buf);

            outputStream.writeTo(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
