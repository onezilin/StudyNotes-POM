package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description: FileInputStream 文件输入流
 */
public class Demo07_FileInputStream {

    @Test
    public void testRead() {
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile())) {
            int readData = 0;
            int count = 0;
            while ((readData = inputStream.read()) != -1) {
                System.out.println((char) readData);
                count++;
            }
            System.out.println(count + "个字节");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReadByte() {
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile())) {
            int readLen = 0;
            byte[] buf = new byte[8];
            while ((readLen = inputStream.read(buf)) != -1) {
                System.out.println(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
