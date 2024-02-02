package com.studynotes.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author onezilin
 * @since 2024-01-26 14:30:56
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 368250027921860843L;
    /**
     * 主键
     */
    private Long userId;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 年龄
     */
    private Long age;

}

