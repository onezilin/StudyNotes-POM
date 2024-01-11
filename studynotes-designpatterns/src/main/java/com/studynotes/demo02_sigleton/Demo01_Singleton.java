package com.studynotes.demo02_sigleton;

/**
 * Description: 饿汉式：在类加载时就创建实例，线程安全
 * <p>
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。
 * <p>
 * 单例模式：保证一个类只有一个实例，并且提供一个访问该实例的全局访问点。
 */
public class Demo01_Singleton {

    private static final Demo01_Singleton SINGLETON = new Demo01_Singleton();

    // 私有无参构造，不能创建实例
    private Demo01_Singleton() {
    }

    public static Demo01_Singleton getSingleton() {
        return SINGLETON;
    }
}
