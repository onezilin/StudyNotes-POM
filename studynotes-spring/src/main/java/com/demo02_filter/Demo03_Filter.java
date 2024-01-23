package com.demo02_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Description: 实现 Filter 接口，对请求和响应进行过滤
 * <p>
 * 注意：Filter 的执行顺序，是根据 Filter 实现类的名称的字符串比较结果决定的，如 Demo03_Filter 的执行顺序在 Demo02_Filter 之后
 */
@WebFilter("/myFilter")
public class Demo03_Filter implements Filter {

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
        System.out.println("进入过滤器2");
        filterChain.doFilter(request, response);
        System.out.println("离开过滤器2");
    }

    /**
     * Description: 服务器关闭时，会调用 destroy 方法，且只会调用一次
     */
    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
