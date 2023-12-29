package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Description: 输入输出流同时操作同一个文件，一边读取，一边写入，会造成读写冲突，源文件内容会全部丢失
 */
public class Demo29_InAndOut {

    @Test
    void test() {
        // 同时对同一个文件定义输入输出流
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile());
             // 由于输出流 append 参数为 false，所以在这一步直接情况相关文件内容
             FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile());) {

            int readData;
            while ((readData = inputStream.read()) != -1) {
                outputStream.write(readData);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 正确的操作方法
    @Test
    void test2() throws IOException {
        FileInputStream inputStream = new FileInputStream(CommonUtil.getFile());

        // 先使用 ArrayList 缓存数据
        ArrayList<Byte> list = new ArrayList<>(1024);
        int readData;
        while ((readData = inputStream.read()) != -1) {
            list.add((byte) readData);
        }

        // append 参数为 false，会将源文件内容清空
        FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile());
        for (byte data : list) {
            outputStream.write(data);
        }

        inputStream.close();
        outputStream.close();
    }
}
