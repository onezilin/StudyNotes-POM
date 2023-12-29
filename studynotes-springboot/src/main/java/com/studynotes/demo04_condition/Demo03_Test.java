package com.studynotes.demo04_condition;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * Description: 测试 @Conditional(Demo01_MyCondition.class) 注解
 */
@Slf4j
@SpringBootTest
public class Demo03_Test {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * Description:
     * 测试步骤：
     * 1、application.yml 中设置 beanCondition.enabled = true，查看结果
     * 2、application.yml 中设置 beanCondition.enabled = false，查看结果
     * <p>
     * 预期：
     * 1、控制台打印出 customBean 是否创建：true
     * 2、控制台打印出 customBean 是否创建：false
     * <p>
     * 结论：
     * <p>
     * `@Conditional(Demo01_MyCondition.class)` 根据 Demo01_MyCondition 中的 matches() 方法返回值决定是否创建 customBean
     */
    @Test
    void magicConditionTest() {
        log.info("customBean 是否创建：{}", applicationContext.containsBean("customBean"));
    }
}
