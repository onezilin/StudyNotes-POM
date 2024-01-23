package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 接收 Demo09_Redirect 重定向的请求
 */
@WebServlet("/demo10_redirect")
public class Demo10_Redirect extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Demo10_Redirect service：" + this);
        response.getWriter().println("Demo10_Redirect"); // 前台页面显示：Demo10_Redirect
    }
}
