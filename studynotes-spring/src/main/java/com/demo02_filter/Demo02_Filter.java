package com.demo02_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Description: 实现 Filter 接口，对请求和响应进行过滤
 *
 * 注解 @WebFilter("/myFilter") 表示对路径为 /myFilter 的请求进行过滤
 */
@WebFilter("/myFilter")
public class Demo02_Filter implements Filter {

    /**
     * Description: 服务器启动时，会调用 init 方法，且只会调用一次
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    /**
     * Description: 对请求进行过滤
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入过滤器1");
        request.setAttribute("name", "zhangsan");
        // 调用 filterChain.doFilter() 方法，才会继续执行后续的 Filter 或 Servlet
        filterChain.doFilter(request, response);
        response.getWriter().write(" hello filter");
        System.out.println("离开过滤器1");
    }

    /**
     * Description: 服务器关闭时，会调用 destroy 方法，且只会调用一次
     */
    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
