package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: HttpServletRequest 用于封装请求信息
 */
@WebServlet("/demo03_httpServletRequest")
public class Demo03_HttpServletRequest extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Demo03_HttpServletRequest service：" + this);

        // 获取请求方式
        System.out.println("请求方式：" + request.getMethod());

        // 获取请求路径
        System.out.println("请求路径：" + request.getRequestURI());

        // 获取请求参数
        System.out.println("请求参数：" + request.getQueryString());

        // 获取请求头
        System.out.println("请求头：" + request.getHeader("User-Agent"));
    }
}
