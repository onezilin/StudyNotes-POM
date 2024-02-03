package com.studynotes.demo04_config.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 账户表(Account)实体类
 *
 * @author onezilin
 * @since 2024-01-26 14:27:02
 */
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = -14800368667417271L;
    /**
     * 主键
     */
    private Long accountId;
    /**
     * 主键
     */
    private Long userId;
    /**
     * 账户余额
     */
    private Long money;

}

