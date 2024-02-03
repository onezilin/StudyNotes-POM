package com.studynotes.demo01_base.entity;

import java.io.Serializable;

/**
 * 身份证表(Card)实体类
 *
 * @author onezilin
 * @since 2024-02-02 17:03:32
 */
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


    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

