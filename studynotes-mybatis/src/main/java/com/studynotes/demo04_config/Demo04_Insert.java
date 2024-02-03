package com.studynotes.demo04_config;

import com.studynotes.demo04_config.dao.UserDao;
import com.studynotes.demo04_config.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

/**
 * Description: 测试插入数据时，通过 <insert /> 标签的 useGeneratedKeys 和 keyProperty 属性获取自增主键
 */
public class Demo04_Insert {

    @Test
    public void test() {
        SqlSessionFactory sqlSessionFactory = Demo01_MybatisConfig.getSqlSessionFactory();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            User user = new User(null, "lisi", 20L);
            int count = mapper.insert(user);
            System.out.println("user: " + user + "插入成功，影响行数：" + count);
        }
    }
}
