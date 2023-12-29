package com.studynotes.java01_basis.demo02_Static;

import org.junit.jupiter.api.Test;

/**
 * Description: 使用 static 实现懒汉式单例模式
 */
public class Demo03_Sigleton {

    @Test
    public void test() {
        Demo03_Sigleton sigleton1 = Demo03_Sigleton.getSigleton();
        Demo03_Sigleton sigleton2 = Demo03_Sigleton.getSigleton();
        System.out.println(sigleton1 == sigleton2); // true
    }

    public static Demo03_Sigleton getSigleton() {
        return GetSigletonClass.SIGLETON;
    }

    public static class GetSigletonClass {
        private static final Demo03_Sigleton SIGLETON = new Demo03_Sigleton();
    }
}
