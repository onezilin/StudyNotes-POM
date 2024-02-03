package com.studynotes.demo04_config.dao;

import com.studynotes.demo01_base.entity.Card;

/**
 * 身份证表(Card)表数据库访问层
 *
 * @author onezilin
 * @since 2024-02-02 17:03:32
 */
public interface CardDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cardId 主键
     * @return 实例对象
     */
    Card queryById(Long cardId);

    /**
     * 新增数据
     *
     * @param card 实例对象
     * @return 影响行数
     */
    int insert(Card card);

    /**
     * 修改数据
     *
     * @param card 实例对象
     * @return 影响行数
     */
    int update(Card card);

    /**
     * 通过主键删除数据
     *
     * @param cardId 主键
     * @return 影响行数
     */
    int deleteById(Long cardId);

    /**
     * Description: 根据用户id查询身份证信息
     */
    Card queryCardByUserId(Long userId);

}

