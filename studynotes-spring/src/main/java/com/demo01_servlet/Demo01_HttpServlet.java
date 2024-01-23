package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 继承 HttpServlet，重写 init、service、destroy 方法
 * <p>
 * 注解 @WebServlet，可以省略在 web.xml 中配置，用于配置 Servlet 的访问路径
 */
@WebServlet("/demo01_httpServlet")
public class Demo01_HttpServlet extends HttpServlet {

    /**
     * Description: 第一次访问时，会创建 Demo01_HttpServlet 实例（单例），调用 init 方法，且只会调用一次
     */
    @Override
    public void init() {
        System.out.println("Demo01_HttpServlet init：" + this);
    }

    /**
     * Description: 每次访问时，都会调用 service 方法
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Demo01_HttpServlet service：" + this);
    }

    /**
     * Description: 服务器关闭时，会调用 destroy 方法，且只会调用一次
     */
    @Override
    public void destroy() {
        System.out.println("Demo01_HttpServlet destroy：" + this);
    }
}
