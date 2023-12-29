package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

/**
 * Description: BufferedReader 的 readLine() 方法
 */
public class Demo23_BufferedReader {

    @Test
    void test() {
        try (StringReader reader = new StringReader("0123");
             BufferedReader bufferedReader = new BufferedReader(reader, 4);) {
            String readData;
            while ((readData = bufferedReader.readLine()) != null) {
                System.out.println(readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 测试 readLine() 死循环的产生
    public static void main(String[] args) {
        String docPath = "E:\\test"; // 文件所在路径 模拟
        File file;
        try {
            file = new File(docPath);
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.err.println(docPath + "文件夹下没有任何文件！");
            } else {
                Arrays.sort(files);
                System.err.println("文件数---" + files.length);
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        InputStreamReader reader;
                        reader = new InputStreamReader(new FileInputStream(files[i]));
                        BufferedReader br = new BufferedReader(reader, 4);
                        String message = "";
                        String line = "";
                        long startTime = System.currentTimeMillis(); // 获取开始时间
                        // 在读取 rar 压缩文件时，陷入死循环
                        while ((line = br.readLine()) != null) {
                            message += line;
                        }
                        br.close();
                        long endTime = System.currentTimeMillis(); // 获取结束时间
                        System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "ms");

                        String fileName = files[i].getName();

                        if (message.trim() == null || message.length() == 0) {
                            System.err.println(fileName + "文件内容为空！");
                        } else {
                            // 上传文件
                            System.err.println("上传===============");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
