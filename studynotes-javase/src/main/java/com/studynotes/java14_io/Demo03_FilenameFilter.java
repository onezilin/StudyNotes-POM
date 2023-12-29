package com.studynotes.java14_io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Description: 分别使用匿名内部类和 lamda 表达式对 FilenameFiletr 进行实现
 */
public class Demo03_FilenameFilter {
    public static FilenameFilter filter(final String afn) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {
            String fn = afn;

            public boolean accept(File dir, String n) {
                // Strip path information:
                String f = new File(n).getName();
                return f.contains(n);
            }
        }; // End of anonymous inner class
    }

    public static void main(String[] args) {
        try {
            File path = new File(".");
            String[] list;
            if (args.length == 0)
                list = path.list();
            else
                list = path.list((dir, n) -> {
                    String f = new File(n).getName();
                    return f.contains(n);
                });
            for (int i = 0; i < list.length; i++)
                System.out.println(list[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
