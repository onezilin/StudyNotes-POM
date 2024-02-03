package com.studynotes.demo04_config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 身份证表(Card)实体类
 *
 * @author onezilin
 * @since 2024-02-02 17:03:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {
    private static final long serialVersionUID = -26079039350666183L;
    /**
     * 主键
     */
    private Long cardId;
    /**
     * 主键
     */
    private Long userId;
    /**
     * 身份证号
     */
    private String number;

}

