package com.demo01_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: Request 对象中的数据在一次请求中有效，通过请求转发的 Request 仍然在一次请求内，我们可以通过 Request + 请求转发的方式，在多个接口中共享数据。
 */
@WebServlet("/demo06_requestScope")
public class Demo06_RequestScope extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Demo06_RequestScope service：" + this);

        // setAttribute() 方法设置请求域对象
        request.setAttribute("name", "requestScope");
        request.getRequestDispatcher("/demo07_requestScope").forward(request, response);
    }
}
