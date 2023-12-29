package com.studynotes.demo10_convert;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Map;

/**
 * Description:
 * application.yml 配置
 * person:
 * dogs:
 * husky: '{"name":"erha","age":3}'
 * corgi: '{"name":"pipi","age":4}'
 */
@Data
@ConfigurationProperties(prefix = Demo01_Config.PREFIX)
public class Demo01_Config {

    public static final String PREFIX = "person";

    @NestedConfigurationProperty
    // 将 dogs 配置映射到此字段上
    private Map<String, Dog> dogs;

    @Data
    public static class Dog {

        private String name;

        private Integer age;
    }
}
