/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.service;

/**
 * TODO
 *
 * @author Infi
 */
public interface CorosEmailService {
    /**
     * 发送邮件
     *
     * @param to       收件人
     * @param subject  邮件主题
     * @param htmlText 邮件内容
     */
    public void sendMail(String to, String subject, String htmlText);
}
