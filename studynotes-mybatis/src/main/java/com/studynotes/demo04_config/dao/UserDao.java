package com.studynotes.demo04_config.dao;

import com.studynotes.demo04_config.entity.User;
import com.studynotes.demo04_config.entity.UserAndAccount;
import com.studynotes.demo04_config.entity.UserAndCard;

/**
 * 用户表(User)表数据库访问层
 *
 * @author onezilin
 * @since 2024-01-26 14:30:56
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Long userId);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    /**
     * Description: 级联查询用户身份证
     */
    UserAndCard queryUserAndCardById1(Long userId);

    /**
     * Description: 级联查询用户身份证
     */
    UserAndCard queryUserAndCardById2(Long userId);

    /**
     * Description: 级联查询用户账户
     */
    UserAndAccount queryUserAndAccountById1(Long userId);

    /**
     * Description: 级联查询用户账户
     */
    UserAndAccount queryUserAndAccountById2(Long userId);

}

