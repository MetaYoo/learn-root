package com.kotall.learn.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/20 16:26
 * @since 1.0.0
 */
@Component
public class MailService {



    public void sendText() {
        MailSender mailSender = new JavaMailSenderImpl();


        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("wangzhongping@ycmedia.cn");
        smm.setTo("wangzhongping@ycmedia.cn");
        smm.setSubject("test");
        smm.setText("this is a test mail, don't reply !");
        mailSender.send(smm);
    }

}
