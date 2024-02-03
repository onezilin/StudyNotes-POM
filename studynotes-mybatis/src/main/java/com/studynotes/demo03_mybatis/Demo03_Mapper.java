package com.studynotes.demo03_mybatis;

import com.studynotes.demo01_base.dao.AnnotationUserDao;
import com.studynotes.demo01_base.dao.UserDao;

/**
 * Description: Mapper 是 MyBatis 中用于定义 SQL 语句和数据操作的接口，
 * 它通过 Java 接口定义了对数据库的操作，可以将数据操作从具体的实现中解耦，提高代码的可维护性和可扩展性。
 * 有两种方式来实现 Mapper 接口：
 * 1、基于 XML 文件实现 {@link UserDao#queryById(Long)}
 * 2、基于注解实现 {@link AnnotationUserDao#queryById(Long)}
 */
public class Demo03_Mapper {

    /**
     * Description:
     * @param null
     * @return
     */
}
