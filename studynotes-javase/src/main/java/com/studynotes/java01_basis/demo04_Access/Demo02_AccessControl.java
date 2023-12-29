package com.studynotes.java01_basis.demo04_Access;

import com.studynotes.java01_basis.demo04_Access.access.Demo01;
import org.junit.jupiter.api.Test;

/**
 * Description: protected 对同包或者子类可见
 */
public class Demo02_AccessControl {

    @Test
    void test() {
        Demo01_AccessControl demo01 = new Demo01_AccessControl();
        // 即便和子类同包，但是 protected 访问权限仍然在 access 包下
        // demo01.b = 2; // 编译报错
        // demo01.method2(); // 编译报错

        Demo01 demo03 = new Demo01();
        // demo03.b = 1; // 编译报错
        // demo03.method2(); // 编译报错
    }
}
