package com.studynotes.java14_io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Description: FilenameFilter 接口可以对 File 获取的 Filepath 进行简单过滤
 */
public class Demo02_FilenameFilter {

    public static void main(String[] args) {
        try {
            File path = new File(".");
            String[] list;
            if (args.length == 0) {
                list = path.list();
            } else {
                list = path.list(new DirFilter(args[0]));
            }
            assert list != null;
            for (String s : list) System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class DirFilter implements FilenameFilter {
        String afn;

        DirFilter(String afn) {
            this.afn = afn;
        }

        public boolean accept(File dir, String name) {
            String f = new File(name).getName();
            return f.contains(afn);
        }
    }
}




