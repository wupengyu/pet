/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.yf.pet.service.impl;

import com.yf.pet.entity.ReturnMessageEnum;
import com.yf.pet.exception.YFException;
import com.yf.pet.service.CorosEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件工具类
 *
 * @author Infi
 */
public class CorosEmailServiceImpl implements CorosEmailService {
    private static final Logger log = LoggerFactory.getLogger(CorosEmailServiceImpl.class);

    private JavaMailSender javaMailSender;

    private String systemEmail;

    @Override
    public void sendMail(String to, String subject, String htmlText) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(msg, "UTF-8");
            msgHelper.setFrom(systemEmail);
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(htmlText, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            log.error(e.toString());
            throw new YFException(ReturnMessageEnum.FAILED_TO_SEND_EMAIL);
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }
}
