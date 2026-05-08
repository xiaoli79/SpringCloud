package common.xiaoli.common.utils;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class Mail {



    private JavaMailSender mailSender;

    private MailProperties mailProperties;


    public Mail(JavaMailSender mailSender,MailProperties mailProperties) {
        this.mailSender = mailSender;
        this.mailProperties = mailProperties;
    }




    public void send(String to,String object,String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,false,"UTF-8");


        String personal = Optional.ofNullable(mailProperties.getProperties().get("personal"))
                .orElse(mailProperties.getUsername());

        helper.setFrom(mailProperties.getUsername(),personal);   //发件人
        helper.setTo(to);  //收件人
        helper.setSubject(object);  //邮件主题
        helper.setText(content,true); //文本信息

        mailSender.send(mimeMessage);
    }
}
