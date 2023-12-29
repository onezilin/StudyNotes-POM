package com.studynotes.java18_jvm;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description: 不同类加载器加载相同的 class 类文件,查看生成的 class 类是否相等
 */
public class Demo16_OverrideClassLoader {

    @Test
    public void test() throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj =
                myLoader.loadClass("com.studynotes.java_se.java18_JVM.Demo16_OverrideClassLoader").newInstance();

        System.out.println(obj.getClass());

        // 证明自己写的类加载器和系统默认的类加载器加载的类不一样
        System.out.println(obj instanceof Demo16_OverrideClassLoader);

        // 生成的对象也不相等
        System.out.println(obj.getClass() == Object.class);

        // 重写的 equal() 方法
        System.out.println(obj.getClass().equals(Object.class));
    }

}


