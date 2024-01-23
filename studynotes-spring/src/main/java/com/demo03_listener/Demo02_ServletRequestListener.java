package com.demo03_listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Description: 实现 ServletRequestListener 接口，监听 ServletRequest 对象的创建和销毁
 */
@WebListener
public class Demo02_ServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequest 对象被销毁了");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequest 对象被创建了");
    }
}
