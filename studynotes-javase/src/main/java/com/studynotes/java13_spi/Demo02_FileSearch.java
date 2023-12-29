package com.studynotes.java13_spi;

/**
 * Description:
 */
public class Demo02_FileSearch implements Demo01_Search {

    @Override
    public void searchDoc(String keyword) {
        System.out.println("文件搜索 " + keyword);
    }
}
