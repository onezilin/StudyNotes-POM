package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Description: 将 OutputStream 转换为 OutputStreamWriter
 */
public class Demo26_OutputStreamWriter {

    @Test
    void test() {
        try (FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile(), true);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);) {

            outputStreamWriter.write('\n');
            outputStreamWriter.write("I Love 祖国");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
