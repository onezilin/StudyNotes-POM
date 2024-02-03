package com.studynotes.demo03_mybatis;

import com.studynotes.demo01_base.dao.AnnotationUserDao;
import com.studynotes.demo01_base.dao.UserDao;
import com.studynotes.demo01_base.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: SqlSession 是 MyBatis 的核心，是执行持久化操作的独享，类似于 JDBC 中的 Connection
 * 1. SqlSession 的生命周期非常短，每次数据库操作都应该获取一个新的 SqlSession 对象
 * 2. SqlSession 对象不是线程安全的，不能被共享，所以不能将 SqlSession 对象作为类的成员变量，需要时再创建
 * 3. SqlSession 提供了对数据库的增删改查方法，以及提交事务、回滚事务、关闭连接等方法
 */
@Slf4j
public class Demo02_SqlSession {

    @Test
    public void test1() {
        SqlSessionFactory sqlSessionFactory = Demo01_SqlSessionFactory.getSqlSessionFactory1();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.queryById(1L);
            log.info("user:{}", user);
            // 提交事务，如果 openSession() 时传入 true，则会自动提交事务，不需要手动提交
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            // 使用完需要将 SqlSession 连接关闭，放回连接池中
            sqlSession.close();
        }
    }

    /**
     * Description: iBatis 方式支持直接通过【namespace + 增删改查标签 id】获取对应的 Mapper XML 配置文件中的 Statement，执行 SQL。
     */
    @Test
    public void test2() {
        SqlSessionFactory sqlSessionFactory = Demo01_SqlSessionFactory.getSqlSessionFactory1();
        // openSession(true) 自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            User user = sqlSession.selectOne("com.studynotes.demo01_base.dao.UserDao.queryById", 1L);
            log.info("user:{}", user);
        }
    }

    @Test
    public void test3() {
        SqlSessionFactory sqlSessionFactory = Demo01_SqlSessionFactory.getSqlSessionFactory2();
        // openSession(true) 自动提交事务
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            AnnotationUserDao userDao = sqlSession.getMapper(AnnotationUserDao.class);
            User user = userDao.queryById(1L);
            log.info("user:{}", user);
        }
    }
}
