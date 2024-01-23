package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: HttpServlet 抽象类的 service()方法中，根据请求方式调用 doGet() 或 doPost() 方法
 */
@WebServlet("/demo02_httpServlet")
public class Demo02_HttpServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Demo02_HttpServlet doGet：" + this);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Demo02_HttpServlet doPost：" + this);
    }
}
