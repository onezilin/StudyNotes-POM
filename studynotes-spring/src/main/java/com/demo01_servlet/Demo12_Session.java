package com.demo01_servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description: Session 和 Cookie 的作用有点类似，都是为了存储用户相关的状态信息，是一种会话跟踪技术。
 */
@WebServlet("/demo12_session")
public class Demo12_Session extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        // 获取 Session
        HttpSession session = request.getSession();
        // 设置 Session 的有效期为 1 天
        session.setMaxInactiveInterval(60 * 60 * 24);
        // 获取 Session 的 ID
        String id = session.getId();
        System.out.println("Session ID：" + id);
        // 设置 Session 的属性，在该 Session 会话内就可以通过 Session 获取该属性
        session.setAttribute("name", "lisi");
    }
}
