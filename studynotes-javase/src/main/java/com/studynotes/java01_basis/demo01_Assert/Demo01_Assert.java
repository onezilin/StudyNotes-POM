package com.studynotes.java01_basis.demo01_Assert;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Description: 断言 assert 关键字，用于表示后面的布尔表达式应该为 true，否则程序中断，抛出 AssertionError 异常。
 * <p>
 * > 注意：用于开发和测试阶段，不用于生产环境。
 */
public class Demo01_Assert {

    @ParameterizedTest
    @CsvSource({"3,1", "3,2", "3,3"})
    public void test(int a, int b) {
        // 断言 a > b 为 true，否则程序中断，抛出 AssertionError 异常，异常信息为 "a > b 出错"
        assert a > b : "a > b 出错";
    }
}



