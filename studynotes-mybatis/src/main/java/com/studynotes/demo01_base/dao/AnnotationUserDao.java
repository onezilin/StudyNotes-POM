package com.studynotes.demo01_base.dao;

import com.studynotes.demo01_base.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 账户表(Account)表数据库访问层
 *
 * @author onezilin
 * @since 2024-01-26 14:26:58
 */
public interface AnnotationUserDao {

    /**
     * 通过ID查询单条数据，和 {@link UserDao#queryById(Long)} 效果一样，
     * UserDao 使用 XML 配置文件方式；AnnotationUserDao 使用注解方式
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Select("select user_id as userId, user_name as userName, age\n" +
            "        from study_notes.user\n" +
            "        where user_id = #{userId}")
    User queryById(@Param("userId") Long userId);
}
