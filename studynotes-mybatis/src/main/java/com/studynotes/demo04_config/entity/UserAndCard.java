package com.studynotes.demo04_config.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 用户和身份证的一对一关系
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserAndCard extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Card card;
}
