package com.studynotes.demo04_config;

import com.studynotes.demo03_interceptor.Demo02_MyInterceptor;
import com.studynotes.demo03_interceptor.Demo03_MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Description: Spring MVC 配置类，用于替代 spring-mvc.xml
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.studynotes")
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * Description: 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Demo02_MyInterceptor myInterceptor1 = new Demo02_MyInterceptor();
        Demo03_MyInterceptor myInterceptor2 = new Demo03_MyInterceptor();
        // 匹配所有路径，除了 /demo01_binding/ 下的路径
        registry.addInterceptor(myInterceptor1).addPathPatterns("/**").excludePathPatterns("/demo01_binding/*");
        registry.addInterceptor(myInterceptor2).addPathPatterns("/**").excludePathPatterns("/demo01_binding/*");
    }

    /**
     * Description: 配置视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // JSP 视图解析器
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        // JstlView 表示 JSP 模板页面需要使用 JSTL 标签库
        irvr.setViewClass(JstlView.class);
        // 页面跳转路径的前缀和后缀
        irvr.setPrefix("/WEB-INF/views/");
        irvr.setSuffix(".jsp");

        registry.viewResolver(irvr);
    }
}
