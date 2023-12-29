package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;

import java.io.File;
import java.io.IOException;

/**
 * Description: File类的基本方法
 */
public class Demo04_File {

    public static void main(String[] args) throws IOException {
        File f = CommonUtil.getFile();

        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        // 获取符合操作系统规范的的文件路径名
        System.out.println(f.getCanonicalPath());
        System.out.println(f.getName());
        System.out.println(f.getParent());
    }
}
