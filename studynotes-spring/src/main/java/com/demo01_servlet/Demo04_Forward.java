package com.demo01_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 使用 HttpServletRequest 进行请求转发
 */
@WebServlet("/demo04_forward")
public class Demo04_Forward extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Demo04_Forward service：" + this);

        // 请求转发，将该请求转发给 demo05_forward
        request.getRequestDispatcher("/demo05_forward").forward(request, response);
        // 请求转发后，由 Demo05_Forward 进行响应，这里虽然执行了，但没有任何效果
        response.getWriter().println("Demo04_Forward");
    }
}
