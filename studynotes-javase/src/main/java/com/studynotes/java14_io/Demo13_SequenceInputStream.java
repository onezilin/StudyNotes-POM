package com.studynotes.java14_io;


import com.studynotes.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Description: SequenceInputStream 将多个字节输入流整合，顺序读取
 */
public class Demo13_SequenceInputStream {
    @Test
    void test() throws FileNotFoundException {
        Vector<InputStream> vector = new Vector<>();
        vector.add(new ByteArrayInputStream("1234567890".getBytes()));
        vector.add(new FileInputStream(CommonUtil.getFile()));
        Enumeration<InputStream> enumeration = vector.elements();

        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);) {
            byte[] buf = new byte[8];
            int readLen = 0;
            while ((readLen = sequenceInputStream.read(buf)) != -1) {
                System.out.println(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
