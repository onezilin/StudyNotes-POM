package com.studynotes.demo04_config;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;

/**
 * Description: 通过 XML 配置文件方式构建 SqlSessionFactory
 */
public class Demo01_MybatisConfig {

    /**
     * Description: 使用配置文件方式构建 SqlSessionFactory
     */
    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactory() {
        Reader resourceAsReader = Resources.getResourceAsReader("mybatis-config-test.xml");
        return new SqlSessionFactoryBuilder().build(resourceAsReader);
    }
}
