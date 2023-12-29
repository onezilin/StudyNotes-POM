package com.studynotes.java01_basis.demo01_Assert;

import org.junit.jupiter.api.Test;

/**
 * Description: 判断是否开启了断言
 */
public class Demo02_Assert {

    @Test
    public void test() {
        boolean isOpen = false;

        // 如果开启了断言，会将isOpen的值改为true
        assert isOpen = true;
        System.out.println(isOpen);
    }
}

