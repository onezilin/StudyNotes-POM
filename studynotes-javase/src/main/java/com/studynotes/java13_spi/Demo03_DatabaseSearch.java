package com.studynotes.java13_spi;

/**
 * Description:
 */
public class Demo03_DatabaseSearch implements Demo01_Search {
    @Override
    public void searchDoc(String keyword) {
        System.out.println("数据库搜索 " + keyword);
    }
}
