package com.studynotes.demo02_sigleton;

/**
 * Description: 静态内部类防晒实现单例模式，原理：
 * * 外部类加载时不会初始化内部类，只有调用内部类的方法时才会初始化
 * * JVM 保证线程安全
 * <p>
 * 优点：线程安全，延迟加载，效率较高
 */
public class Demo03_Singleton {

    private Demo03_Singleton() {
    }

    public static Demo03_Singleton getSingleton() {
        return TestTemp.test;
    }

    private static class TestTemp {
        public static final Demo03_Singleton test = new Demo03_Singleton();
    }
}
