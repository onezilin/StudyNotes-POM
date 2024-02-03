package com.studynotes.demo04_config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author onezilin
 * @since 2024-01-26 14:30:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// 为 User 类起别名
@Alias("userAnnotation")
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

