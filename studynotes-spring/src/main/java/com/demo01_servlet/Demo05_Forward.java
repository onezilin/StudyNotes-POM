package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 处理 Demo04_Forward 转发的请求
 */
@WebServlet("/demo05_forward")
public class Demo05_Forward extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Demo05_Forward service：" + this);
        response.getWriter().println("Demo05_Forward"); // 前台页面显示：Demo05_Forward
    }
}
