package com.studynotes.demo04_condition;

import com.studynotes.bean.CustomBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 自定义配置类
 */
@Configuration
public class Demo02_MyConfigration {

    /**
     * Description: 当 {@link Demo01_MyCondition#matches} 返回值为 true 时，才会注册 @Bean 注解所在方法的 bean
     */
    @Conditional(Demo01_MyCondition.class)
    @Bean
    public CustomBean customBean() {
        return new CustomBean();
    }
}


