package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Description: 处理 Demo06_requestScope 转发的请求，并获取 Request 请求域对象
 */
@WebServlet("/demo07_requestScope")
public class Demo07_requestScope extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("Demo07_requestScope service：" + this);

        request.setCharacterEncoding("UTF-8");

        // 获取请求域对象
        Object name = request.getAttribute("name");
        System.out.println("请求域对象：" + name);
    }
}
