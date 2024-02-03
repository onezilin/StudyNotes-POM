package com.studynotes.demo04_config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: 自定义 TypeHandler，用于处理 Java 类型和 JDBC 类型之间的转换
 */
// 匹配指定的 Java 类型
@MappedTypes(String.class)
// 匹配指定的 JdbcType 枚举类型
@MappedJdbcTypes(JdbcType.VARCHAR)
@Slf4j
public class Demo03_TypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        log.info("使用我的 TypeHandler");
        preparedStatement.setString(i, s);
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        log.info("使用我的 TypeHandler，ResultSet 通过列名获取字符串");
        return resultSet.getString(s);
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        log.info("使用我的 TypeHandler，ResultSet 通过下标获取字符串");
        return resultSet.getString(i);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        log.info("使用我的 TypeHandler，CallableStatement 通过下标获取字符串");
        return callableStatement.getString(i);
    }
}
