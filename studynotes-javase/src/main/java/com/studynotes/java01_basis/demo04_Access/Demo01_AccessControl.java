package com.studynotes.java01_basis.demo04_Access;

import com.studynotes.java01_basis.demo04_Access.access.Demo01;
import org.junit.jupiter.api.Test;

/**
 * Description: protected 对同包或者子类可见
 * <p>
 * 无论是否同包，子类中继承的 protected 构造器、方法、属性仅能在子类中使用
 */
public class Demo01_AccessControl extends Demo01 {

    public Demo01_AccessControl() {
        // 仅能以 super 调用父类的 protected 构造器
        super(3);
        Demo01 hhhhh = new Demo01();
    }

    // 仅能使用父类的 protected 方法，不能使用父类实例去调用的 protected 属性
    @Test
    void test() {
        Demo01_AccessControl demo01 = new Demo01_AccessControl();
        demo01.b = 2;
        demo01.method2();
    }

    @Test
    void test2() {
        Demo01 demo01 = new Demo01();
        // demo01.b = 1; // 编译报错
        // demo01.method2(); // 编译报错
    }

    protected void test3() {
    }
}
