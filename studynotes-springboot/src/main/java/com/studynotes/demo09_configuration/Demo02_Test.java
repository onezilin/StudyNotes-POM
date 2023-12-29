package com.studynotes.demo09_configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description: 测试
 */
@SpringBootTest
public class Demo02_Test {

    /**
     * 测试步骤：
     * 1. 启动项目
     * <p>
     * 预期：
     * customBean1 等于 customBean2，指向同一个对象
     * <p>
     * 结论：
     * `@Configuration` 注解的类被 CGLIB 增强，会设置一个拦截器专门对 @Bean 方法进行拦截处理，
     * 通过依赖查找的方式从 IoC 容器中获取 Bean 对象，**如果是单例 Bean，那么每次都是返回同一个对象**
     */
    @Test
    public void test() {
    }
}
