package com.studynotes.demo02_sigleton;

/**
 * Description: 懒汉式(双重检查机制)，并且使用 volatile 关键字，防止指令重排
 * <p>
 * 优点：线程安全，延迟加载，效率较高
 * 缺点：写法复杂
 */
public class Demo02_Singleton {

    private static volatile Demo02_Singleton test;

    private Demo02_Singleton() {
    }

    public static Demo02_Singleton getSingleton() {
        if (test == null) {
            synchronized (Demo02_Singleton.class) {
                if (test == null) {
                    test = new Demo02_Singleton();
                }
            }
        }
        return test;
    }
}
