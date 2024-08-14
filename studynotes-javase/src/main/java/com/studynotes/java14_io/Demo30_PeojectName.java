package com.studynotes.java14_io;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * Description:
 */
public class Demo30_PeojectName {

    @Test
    public void test() {
        String projectClassDirName = Objects.requireNonNull(getClass().getResource("/")).getPath();
        System.out.println("当前项目编译后的 classPath 路径为: " + projectClassDirName);
    }
}
