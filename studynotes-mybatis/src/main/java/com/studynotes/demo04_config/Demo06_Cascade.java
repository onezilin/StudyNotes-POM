package com.studynotes.demo04_config;

import com.studynotes.demo04_config.dao.UserDao;
import com.studynotes.demo04_config.entity.UserAndAccount;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: 级联查询，User 和 Account 一对多关系
 */
public class Demo06_Cascade {

    @Test
    public void test() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);

            UserAndAccount userAndAccount1 = mapper.queryUserAndAccountById1(1L);
            System.out.println("userAndAccount1: " + userAndAccount1);
        }
    }

    /**
     * Description: 测试懒加载
     * 测试步骤：
     * * 1、执行程序
     * * 2、在 mybatis-config.xml 中配置懒加载或者在 association 或 collection 标签中配置懒加载，执行程序（不打断点）
     * <p>
     * 预期结果：
     * * 1、先打印第一条 SQL 查询语句（queryUserAndAccountById2），再打印第二条 SQL 查询语句（queryAccountByUserId），再打印 'lazy loading...'
     * * 2、先打印第一条 SQL 查询语句（queryUserAndAccountById2），再打印 'lazy loading...'，再打印第二条 SQL 查询语句（queryAccountByUserId）
     * <p>
     * 结论：
     * * 1、懒加载是在访问关联的数据（在这里是 userAndAccount2.getAccounts() 访问 accounts）的时候才加载，而不是一开始就加载
     */
    @Test
    public void test2() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);

            UserAndAccount userAndAccount2 = mapper.queryUserAndAccountById2(1L);

            System.out.println("lazy loading...");
            System.out.println("user_id: " + userAndAccount2.getUserId());
            System.out.println("accounts: " + userAndAccount2.getAccounts());
        }
    }
}
