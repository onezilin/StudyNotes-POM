package com.studynotes.demo03_mybatis;

import com.studynotes.constant.CommonConstants;
import com.studynotes.demo01_base.dao.AnnotationUserDao;
import lombok.SneakyThrows;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.Reader;

/**
 * Description: SqlSessionFactory 的两种构建方式：配置文件方式、编码方式
 */
public class Demo01_SqlSessionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * Description: 使用配置文件方式构建 SqlSessionFactory
     */
    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactory1() {
        Reader resourceAsReader = Resources.getResourceAsReader("mybatis-config.xml");

        // 构建 SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
        return sqlSessionFactory;
    }

    /**
     * Description: 使用编码方式构建 SqlSessionFactory（不推荐）
     */
    public static SqlSessionFactory getSqlSessionFactory2() {
        // 构建 Configuration
        Configuration configuration = new Configuration();

        // 使用 JDBC 事务管理
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 数据库连接池
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(CommonConstants.MYSQL_DRIVER);
        dataSource.setUrl(CommonConstants.MYSQL_URL);
        dataSource.setUsername(CommonConstants.MYSQL_USERNAME);
        dataSource.setPassword(CommonConstants.MYSQL_PASSWORD);
        // MySQL 数据库连接配置
        Environment environment = new Environment("development", transactionFactory, dataSource);
        configuration.setEnvironment(environment);

        // 加载单个注解映射接口
        configuration.addMapper(AnnotationUserDao.class);

        // 构建 SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
