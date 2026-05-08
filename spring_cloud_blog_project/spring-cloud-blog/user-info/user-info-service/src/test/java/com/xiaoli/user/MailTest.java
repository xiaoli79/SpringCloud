package com.xiaoli.user;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void test() throws Exception {

        // 创建一个邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();

        // 创建 MimeMessageHelper
        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        // 发件人邮箱和名称
        helper.setFrom("3099592185@qq.com", "bite博客系统");

        // 收件人邮箱
        helper.setTo("3346036050@qq.com");

        // 邮件标题
        helper.setSubject("Hello");

        // 邮件正文，第二个参数表示是否是HTML正文
        helper.setText("Hello <strong> World</strong>！", true);

        // 发送
        javaMailSender.send(message);
    }
}