package com.studynotes.demo10_convert;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Description: @ConfigurationPropertiesBinding 注解，可以将配置文件中的配置值转换为其他类型
 */
@Component
@ConfigurationPropertiesBinding
public class Demo02_DogConvert implements Converter<String, Demo01_Config.Dog> {
    @Override
    public Demo01_Config.Dog convert(String source) {
        // 将 String 类型转化为 Dog 类型
        return JSONObject.parseObject(source, Demo01_Config.Dog.class);
    }
}
