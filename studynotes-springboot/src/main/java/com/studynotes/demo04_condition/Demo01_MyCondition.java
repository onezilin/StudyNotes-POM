package com.studynotes.demo04_condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Description: 实现 Condition 接口
 */
public class Demo01_MyCondition implements Condition {

    private static final String BEAN_CONDITION_ENABLED = "beanCondition.enabled";

    /**
     * Description: application.properties 中存在 beanCondition.enabled 配置且为 true 时，返回值为 true
     *
     * @param context  Spring 中的上下文相关信息，例如：BeanDefinetionRegistry、Environment 等
     * @param metadata 可以获取指定类上的注解信息
     * @return boolean 返回值为 true 时，才会注册 @Bean 注解所在方法的 bean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        if (!env.containsProperty(BEAN_CONDITION_ENABLED)) {
            return false;
        }
        return "true".equals(env.getProperty(BEAN_CONDITION_ENABLED));
    }
}
