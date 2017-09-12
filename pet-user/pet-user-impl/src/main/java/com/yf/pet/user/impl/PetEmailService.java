package com.yf.pet.user.impl;

import com.yf.pet.common.ApplicationConstants;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.utils.YFResourceUtil;
import com.yf.pet.common.exception.YFException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/1
 */
public class PetEmailService {
    private static final Logger log = LoggerFactory.getLogger(PetEmailService.class);

    private JavaMailSender javaMailSender;

    private String systemEmail;

    public static String resetPwdEmailAddress = YFResourceUtil.getValueByKey(ApplicationConstants.CONFIG_PATH,"pet.email.reset.password");

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


    /**
     * 创建密码重置邮件内容
     *
     * @param sendTo    收件人
     * @param validCode 随机验证码
     * @return 邮件html内容
     */
    public static  String createResetPwdHtml(String sendTo, String validCode) {
        Date nowDate = new Date();
        // 1. 设置邮件发送时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        // 3. 设置邮件内容
        StringBuffer htmlMsg = new StringBuffer();
        htmlMsg.append("<html><head><meta charset=\"UTF-8\"><title>YFTECH</title></head><body>");
        htmlMsg.append(
                "<div style=\"padding: 0 5% 18px; font: 300 14px/18px 'Lucida Grande', Lucida Sans, Lucida Sans Unicode, sans-serif, Arial, Helvetica, Verdana, sans-serif; color: #333; position: relative; font-size: 14px; height: auto; padding: 15px 15px 10px 15px; z-index: 1; zoom: 1; line-height: 1.7;\">");
        htmlMsg.append("<p>Hello ");
        // 4.  显示用户邮箱前缀前的名字
        String nickname = StringUtils.substringBefore(sendTo, "@");
        htmlMsg.append(nickname);
        htmlMsg.append("！</p>");
        htmlMsg.append("<p>We received a request to reset your password. Click the link below to choose a new one: </p>");
        // 5. 链接地址
        htmlMsg.append("<p>");
        htmlMsg.append("<a href=\"");
        htmlMsg.append(resetPwdEmailAddress);
        htmlMsg.append("?email=");
        htmlMsg.append(sendTo);
        htmlMsg.append("&checkCode=");
        htmlMsg.append(validCode);
        htmlMsg.append("\">Reset password</a>");
        htmlMsg.append("</p>");
        // 5. 获取密码重置页面地址
        htmlMsg.append("<p>This link will only be valid for 1 hour.Your password won't be changed if you ignore this email. </p>");
        htmlMsg.append("<p>Best wishes! </p>");
//        htmlMsg.append("<p>Pet Crew</p>");
        htmlMsg.append("</div></body></html>");
        return htmlMsg.toString();
    }
}
