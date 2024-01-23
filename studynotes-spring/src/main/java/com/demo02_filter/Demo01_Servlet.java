package com.demo02_filter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 用于测试 Filter 的访问路径对应的 Servlet
 */
@WebServlet("/myFilter")
public class Demo01_Servlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object name = request.getAttribute("name");
        System.out.println(name);

        response.getWriter().write("hello");
    }
}
