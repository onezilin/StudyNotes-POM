package com.studynotes.demo02_jdbc;

import com.studynotes.constant.CommonConstants;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;

/**
 * Description: 使用 JDBC 连接、操作 MySQL 数据库
 * <p>
 * 执行步骤：
 * 1. 加载 MySQL 驱动
 * 2. 获取数据库连接
 * 3. 创建 Statement 对象
 * 4. 执行 SQL 语句
 * 5. 处理结果集
 * 6. 释放资源
 * 7. 关闭连接
 */
public class Demo01_JdbcExample {

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
            closeConnection(preparedStatement, resultSet, connection);
        }
    }

    /**
     * Description: 获取 JDBC 连接
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(CommonConstants.MYSQL_DRIVER);
            connection = DriverManager.getConnection(CommonConstants.MYSQL_URL, CommonConstants.MYSQL_USERNAME,
                    CommonConstants.MYSQL_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Description: 关闭 JDBC 连接，释放资源
     */
    private void closeConnection(Statement statement, ResultSet resultSet, Connection connection) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null && !statement.isClosed())
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
