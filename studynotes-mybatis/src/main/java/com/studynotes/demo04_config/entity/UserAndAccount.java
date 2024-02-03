package com.studynotes.demo04_config.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 用户和账户的一对多关系
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserAndAccount extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Account> accounts;
}
