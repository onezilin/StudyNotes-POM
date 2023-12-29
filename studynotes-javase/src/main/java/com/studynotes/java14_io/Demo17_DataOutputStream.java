package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * Description: DataOutputStream 装饰器模式增强写入数据，搭配 DataInputStream 获取写入的数据
 */
public class Demo17_DataOutputStream {

    @Test
    void test() {
        try (FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile());
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
             FileInputStream inputStream = new FileInputStream(CommonUtil.getFile());
             DataInputStream dataInputStream = new DataInputStream(inputStream)) {

            dataOutputStream.writeChars("I Love 中国");

            char readData;
            while (dataInputStream.available() > 0) {
                readData = dataInputStream.readChar();
                System.out.println(readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
