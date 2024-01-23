package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 通过 HttpServletResponse 向前台页面输出内容
 */
@WebServlet("/demo08_httpServletResponse")
public class Demo08_HttpServletResponse extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Demo08_HttpServletResponse service：" + this);
        response.getWriter().println("Demo08_HttpServletResponse"); // 前台页面显示：Demo08_HttpServletResponse
    }
}
