package com.studynotes.demo04_config.dao;

import com.studynotes.demo04_config.entity.Account;

import java.util.List;

/**
 * 账户表(Account)表数据库访问层
 *
 * @author onezilin
 * @since 2024-01-26 14:26:58
 */
public interface AccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param accountId 主键
     * @return 实例对象
     */
    Account queryById(Long accountId);

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int insert(Account account);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int update(Account account);

    /**
     * 通过主键删除数据
     *
     * @param accountId 主键
     * @return 影响行数
     */
    int deleteById(Long accountId);

    /**
     * Description: 根据用户id查询账户信息
     */
    List<Account> queryAccountByUserId(Long userId);

}

