package com.yf.pet.service.impl;

import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.exception.YFException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
@Service("emailService")
public class PetEmailService {
    private static final Logger log = LoggerFactory.getLogger(PetEmailService.class);

    private JavaMailSender javaMailSender;

    private String systemEmail;

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
