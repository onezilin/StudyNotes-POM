package com.studynotes.java09_typeInfo;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Description:
 * - Class.getResource()：路径如果以 `/` 开头，那么会**以 classpath 为根路径**去查找资源；如果不以 `/` 开头，那么会**以这个类的 class 文件所在的路径为根路径**去查找资源。
 * - ClassLoader.getResource()：ClassLoader 并不关心包路径，它永远会**以 classpath 为根路径**去查找资源，并且路径不需要以 `/` 开头，所有以 `/` 开头的路径都返回 null。
 */
public class Demo07_ClassLoader {

    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Demo07_ClassLoader.class.getClassLoader();

        Enumeration<URL> urls = classLoader.getResources(FACTORIES_RESOURCE_LOCATION);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        URL url = Demo07_ClassLoader.class.getResource("/" + FACTORIES_RESOURCE_LOCATION);
        System.out.println(url);
    }
}
