package com.studynotes.demo10_convert;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description: 测试 @ConfigurationPropertiesBinding 注解
 */
@Slf4j
@SpringBootTest
@EnableConfigurationProperties(Demo01_Config.class)
public class Demo03_Test {

    @Resource
    private Demo01_Config personApplicationConfig;

    @Test
    public void test() {
        Map<String, Demo01_Config.Dog> dogs = personApplicationConfig.getDogs();
        log.info("dogs {}", dogs);
    }
}
