package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Description: BufferedWriter 先将数据写入缓存，待缓存满时，再将数据写入终端
 */
public class Demo27_BufferedWriter {

    @Test
    void test() throws IOException {
        FileWriter fileWriter = new FileWriter(CommonUtil.getFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write('\n');
            bufferedWriter.write("new 的数据");
            // 需要刷新，才能在缓存未满时，将缓存中的数据写入终端
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
