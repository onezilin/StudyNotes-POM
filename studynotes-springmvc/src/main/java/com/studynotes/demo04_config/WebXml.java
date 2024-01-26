package com.studynotes.demo04_config;

import com.studynotes.SpringMVCMain;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Description: web.xml 配置类，用于替代 web.xml，且会自动被 SpringServletContainerInitializer 获取
 */
public class WebXml extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Description: 指定 Spring 配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringMVCMain.class};
    }

    /**
     * Description: 指定 SpringMVC 配置类
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    /**
     * Description: 指定 DispatcherServlet 的映射路径
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/test"};
    }
}
