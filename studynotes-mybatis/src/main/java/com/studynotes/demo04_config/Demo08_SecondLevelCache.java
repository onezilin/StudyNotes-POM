package com.studynotes.demo04_config;

import com.studynotes.demo04_config.dao.UserDao;
import com.studynotes.demo04_config.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: 二级缓存，可以被多个 SqlSession 共享
 */
public class Demo08_SecondLevelCache {

    /**
     * Description:
     * 测试步骤：
     * 1、在 UserDao.xml 中添加 <cache /> 标签开启二级缓存，执行程序
     * <p>
     * 预期结果：
     * 1、sqlSession1 第一次查询，会查询数据库，打印 SQL 语句；
     * 2、第二次查询，不会查询数据库，直接从缓存中获取
     * <p>
     * 结论：
     * 1、二级缓存是跨 SqlSession 的，多个 SqlSession 可以共享二级缓存。
     * 2、只有在提交事务或关闭 SqlSession 时，才会将数据写入到二级缓存中。
     */
    @Test
    public void test1() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession1 = sqlSessionFactory.openSession(true)) {
            UserDao mapper1 = sqlSession1.getMapper(UserDao.class);

            // sqlSession1 第一次查询
            User user1 = mapper1.queryById(1L);
            System.out.println("user: " + user1);
        }

        try (SqlSession sqlSession2 = sqlSessionFactory.openSession(true)) {
            UserDao mapper2 = sqlSession2.getMapper(UserDao.class);

            // sqlSession2 第一次查询
            User user2 = mapper2.queryById(1L);
            System.out.println("user: " + user2);
        }
    }

    /**
     * Description:
     * 测试步骤：
     * 1、执行程序
     * 2、将 update 操作对应的 <update> 标签的 flushCache 属性设置为 false
     * <p>
     * 预期结果：
     * 1、第一次查询，会查询数据库，打印 SQL 语句；执行修改操作；第二次查询，会查询数据库，打印 SQL 语句
     * 2、flushCache 属性设置为 false，第一次查询，会查询数据库，打印 SQL 语句；执行修改操作；第二次查询，会查询数据库，打印 SQL 语句
     * <p>
     * 结论：
     * 1、如果 SqlSession 生命周期内执行了增删改 SQL 语句，则会清空此 SqlSession 中的所有缓存
     * 2、flushCache 属性设置为 false，执行修改操作，不会清空此 SqlSession 中的所有缓存
     */
    @Test
    public void test2() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession1 = sqlSessionFactory.openSession(true)) {
            UserDao mapper1 = sqlSession1.getMapper(UserDao.class);

            // sqlSession1 第一次查询
            User user1 = mapper1.queryById(1L);
            System.out.println("user: " + user1);
        }

        try (SqlSession sqlSession2 = sqlSessionFactory.openSession(true)) {
            UserDao mapper2 = sqlSession2.getMapper(UserDao.class);

            mapper2.update(new User(1L, "张三", 25L));

            // sqlSession2 第一次查询
            User user2 = mapper2.queryById(1L);
            System.out.println("user: " + user2);
        }
    }
}
