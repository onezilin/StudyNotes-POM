package com.studynotes.demo01_base.dao;

import com.studynotes.demo01_base.entity.Card;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

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
     * 查询指定行数据
     *
     * @param card 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Card> queryAllByLimit(Card card, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param card 查询条件
     * @return 总行数
     */
    long count(Card card);

    /**
     * 新增数据
     *
     * @param card 实例对象
     * @return 影响行数
     */
    int insert(Card card);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Card> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Card> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Card> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Card> entities);

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

}

