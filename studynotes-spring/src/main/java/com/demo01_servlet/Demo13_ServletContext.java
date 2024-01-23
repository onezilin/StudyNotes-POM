package com.demo01_servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: ServletContext 是 Web 应用的上下文，当 WEB 容器启动时，会创建对应的 ServletContext 对象，ServletContext 与 WEB 应用有相同的生命周期。
 *
 * ServletContext 对象的作用：
 * * 用于在整个Web应用中共享数据。
 * * 该对象存储当前应用程序的相关信息。
 */
@WebServlet("/demo13_servletContext")
public class Demo13_ServletContext extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        // ServletContext 对象的获取方式：
        // 1、获取 ServletContext 对象
        ServletContext servletContext = getServletContext();
        // 2、通过 request 对象获取 ServletContext 对象
        servletContext = request.getServletContext();
        // 3、通过 Session 对象获取 ServletContext 对象
        servletContext = request.getSession().getServletContext();

        // 获取 ServletContext 的初始化参数
        String initParameter = servletContext.getInitParameter("name");
        System.out.println("ServletContext 的初始化参数：" + initParameter);
        // 获取 ServletContext 的真实路径
        String realPath = servletContext.getRealPath("/demo13_servletContext");
        System.out.println("ServletContext 的真实路径：" + realPath);
        // 设置 ServletContext 的属性，在该应用内就可以通过 ServletContext 获取该属性
        servletContext.setAttribute("name", "zhangsan");
    }
}
