package com.studynotes.demo02_jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.studynotes.constant.CommonConstants;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: 使用 JDBC 连接、操作 MySQL 数据库
 * <p>
 * 不同点在于：使用 Druid 数据库连接池管理 Connection 连接，而不是直接使用 DriverManager 获取连接，其它代码都一样
 */
public class Demo02_Druid {

    private static final DruidDataSource DATA_SOURCE = new DruidDataSource();

    static {
        // 基本配置
        DATA_SOURCE.setDriverClassName(CommonConstants.MYSQL_DRIVER);
        DATA_SOURCE.setUrl(CommonConstants.MYSQL_URL);
        DATA_SOURCE.setUsername(CommonConstants.MYSQL_USERNAME);
        DATA_SOURCE.setPassword(CommonConstants.MYSQL_PASSWORD);
        // 其它配置
        DATA_SOURCE.setInitialSize(2);
        DATA_SOURCE.setMinIdle(2);
        DATA_SOURCE.setMaxActive(5);
        DATA_SOURCE.setValidationQuery("select 1");
        DATA_SOURCE.setTestWhileIdle(true);
        DATA_SOURCE.setTestOnBorrow(true);
        DATA_SOURCE.setTestOnReturn(false);
        DATA_SOURCE.setMaxWait(6000);
    }

    @ParameterizedTest
    @CsvSource({"1", "2"})
    public void testJdbc(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from study_notes.user where user_id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Description: 通过 Druid 数据库连接池获取连接
     */
    private Connection getConnection() {
        Connection connection;
        try {
            connection = DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * Description: 关闭连接，将连接放回连接池
     */
    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
