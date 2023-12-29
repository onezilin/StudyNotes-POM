package com.studynotes.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Role)实体类
 * <p>
 * create table role
 * (
 * id        int unsigned auto_increment
 * primary key,
 * role_name char(10)            not null,
 * age       tinyint unsigned    null,
 * sex       tinyint(1) unsigned null comment '0：男；1：女'
 * );
 */
@Data
// 使用注解方式起别名
// @Alias("role")
public class Role implements Serializable {

    private static final long serialVersionUID = 347902023782832401L;

    private Integer id;

    private String roleName;

    private Byte age;

    /**
     * 0：男；1：女
     */
    private Byte sex;
}
