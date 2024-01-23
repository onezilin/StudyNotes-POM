package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: HttpServletResponse 进行重定向
 * <p>
 * 客户端发出第一个请求后，被服务端接收处理后，服务端会响应给客户端一个新的地址，当客户端接收到响应后，会立刻根据新的地址再进行一次请求，由新的地址去接受请求并响应。
 */
@WebServlet("/demo09_redirect")
public class Demo09_Redirect extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Demo09_Redirect service：" + this);
        response.sendRedirect("/demo10_redirect"); // 重定向
    }
}
