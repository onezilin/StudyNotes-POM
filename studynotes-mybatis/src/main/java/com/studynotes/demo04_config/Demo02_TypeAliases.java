package com.studynotes.demo04_config;

import com.studynotes.demo04_config.dao.UserDao;
import com.studynotes.demo04_config.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: 为 com.studynotes.demo04_config.entity.User 设置别名
 */

public class Demo02_TypeAliases {

    /**
     * Description:
     * 测试步骤：
     * 1、在 mybatis-config-test.xml 中添加 `<typeAliases>` 标签，在 Mybatis 上下文（即基于 XML 配置文件或注解解方式的 Mapper）中使用
     * 2、第一种方式：基于 XML 配置文件，分别为指定 Java 类添加别名
     * 3、第二种方式：基于 @Alias 注解，扫描指定包下被 @Alias 注解的 Java 类
     * 4、在 com/studynotes/demo04_config/mapper/UserDao.xml 的 <resultMap type=""/> 分别使用第一种和第二种方式的别名，查看是否生效
     */
    @Test
    public void test() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            User user = mapper.queryById(1L);
            System.out.println("user: " + user);
        }
    }
}
