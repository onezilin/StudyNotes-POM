package com.studynotes.demo04_config;

import com.studynotes.demo04_config.dao.UserDao;
import com.studynotes.demo04_config.entity.UserAndCard;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: 级联查询，User 和 Card 一对一关系
 */
public class Demo05_Cascade {

    @Test
    public void test() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);

            UserAndCard userAndCard1 = mapper.queryUserAndCardById1(1L);
            System.out.println("userAndCard1: " + userAndCard1);

            UserAndCard userAndCard2 = mapper.queryUserAndCardById2(1L);
            System.out.println("userAndCard2: " + userAndCard2);
        }
    }
}
