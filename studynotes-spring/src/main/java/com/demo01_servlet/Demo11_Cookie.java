package com.demo01_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: Cookie 用于存储少量的数据，在每次请求时，都会携带 Cookie 信息发送给服务端，在每次响应时，也会携带 Cookie 信息返回给客户端。
 */
@WebServlet("/demo11_cookie")
public class Demo11_Cookie extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            System.out.println("没有 Cookie");
        } else {
            for (Cookie c : cookies) {
                System.out.println(c.getName() + " : " + c.getValue());
            }
        }

        // 创建 Cookie 对象
        Cookie cookie = new Cookie("name", "zhangsan");
        // 设置 Cookie 的有效期为 1 天
        cookie.setMaxAge(60 * 60 * 24);
        // 设置 Cookie 的有效路径
        cookie.setPath("/demo11_cookie");
        // 将 Cookie 添加到响应中
        response.addCookie(cookie);
    }
}
