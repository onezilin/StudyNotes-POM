package com.studynotes.demo04_config;

import com.studynotes.demo04_config.dao.UserDao;
import com.studynotes.demo04_config.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: MyBatis 一级缓存（默认开启）
 * <p>
 * 特点：
 * * 一级缓存只在 SqlSession 生命周期内有效。
 * * 每个 `namespace + selectId` 的首次查询结果都会缓存，接下来对于 `namespace + selectId` 的查询不会查询数据库，而是直接从缓存中获取。
 * * 如果 SqlSession 生命周期内执行了增删改 SQL 语句，则会清空此 SqlSession 中的所有缓存。
 */
public class Demo07_FirstLevelCache {

    /**
     * Description:
     * 测试步骤：
     * 1、执行程序
     * <p>
     * 预期结果：
     * 1、sqlSession1 第一次查询，会查询数据库，打印 SQL 语句
     * 2、sqlSession2 第一次查询，会查询数据库，打印 SQL 语句
     * <p>
     * 结论：
     * 1、一级缓存只在 SqlSession 生命周期内有效
     */
    @Test
    public void test1() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
             SqlSession sqlSession2 = sqlSessionFactory.openSession(true)) {
            UserDao mapper1 = sqlSession1.getMapper(UserDao.class);
            UserDao mapper2 = sqlSession2.getMapper(UserDao.class);

            // sqlSession1 第一次查询
            User user1 = mapper1.queryById(1L);
            System.out.println("user: " + user1);

            // sqlSession2 第一次查询
            User user2 = mapper2.queryById(1L);
            System.out.println("user: " + user2);
        }
    }

    /**
     * Description:
     * 测试步骤：
     * 1、执行程序
     * <p>
     * 预期结果：
     * 1、sqlSession1 第一次查询，会查询数据库，打印 SQL 语句
     * 2、sqlSession2 进行修改
     * 3、sqlSession1 第二次查询，不会查询数据库，直接从缓存中获取
     * <p>
     * 结论：
     * 1、一级缓存只在 SqlSession 生命周期内有效，在多线程环境下，一级缓存会出现问题，查询到脏数据
     */
    @Test
    public void test2() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
             SqlSession sqlSession2 = sqlSessionFactory.openSession(true)) {
            UserDao mapper1 = sqlSession1.getMapper(UserDao.class);
            UserDao mapper2 = sqlSession2.getMapper(UserDao.class);

            // sqlSession1 第一次查询
            User user1 = mapper1.queryById(1L);
            System.out.println("user: " + user1);

            // sqlSession2 进行修改
            mapper2.update(new User(1L, "zhangsan", 22L));

            // sqlSession1 第二次查询
            User user2 = mapper1.queryById(1L);
            System.out.println("user: " + user2);
        }
    }

    /**
     * Description:
     * 测试步骤：
     * 1、执行程序
     * <p>
     * 预期结果：
     * 1、第一次查询，会查询数据库，打印 SQL 语句
     * 2、第二次查询，不会查询数据库，直接从缓存中获取
     * <p>
     * 结论：
     * 1、每个 `namespace + selectId` 的首次查询结果都会缓存，接下来**相同 `namespace + selectId` 和参数**的查询不会查询数据库，而是直接从缓存中获取
     */
    @Test
    public void test3() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);

            // 第一次查询
            User user1 = mapper.queryById(1L);
            System.out.println("user: " + user1);

            // 第二次查询
            User user2 = mapper.queryById(1L);
            System.out.println("user: " + user2);
        }
    }

    /**
     * Description:
     * 测试步骤：
     * 1、执行程序
     * 2，将 update 操作对应的 <update> 标签的 flushCache 属性设置为 false
     * <p>
     * 预期结果：
     * 1、第一次查询，会查询数据库，打印 SQL 语句；执行修改操作；第二次查询，会查询数据库，打印 SQL 语句
     * 2、flushCache 属性设置为 false，第一次查询，会查询数据库，打印 SQL 语句；执行修改操作；第二次查询，会查询数据库，打印 SQL 语句
     * <p>
     * 结论：
     * 1、如果 SqlSession 生命周期内执行了增删改 SQL 语句，则会清空此 SqlSession 中的所有缓存
     * 2、flushCache 属性设置为 false，执行修改操作，也会清空此 SqlSession 中的所有缓存，说明增删改的 flushCache 对一级缓存无效
     */
    @Test
    public void test4() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);

            // 第一次查询
            User user1 = mapper.queryById(1L);
            System.out.println("user: " + user1);

            // 执行修改操作
            mapper.update(new User(1L, "zhangsan", 21L));

            // 第二次查询
            User user2 = mapper.queryById(1L);
            System.out.println("user: " + user2);
        }
    }
}
