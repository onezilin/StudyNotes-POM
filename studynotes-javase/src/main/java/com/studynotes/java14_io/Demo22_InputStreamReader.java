package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Description:
 */
public class Demo22_InputStreamReader {

    @Test
    void test() {
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile());
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("GBK"));) {

            int readData;
            while ((readData = inputStreamReader.read()) != -1) {
                System.out.println((char) readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
