package common.xiaoli.common.config;


import common.xiaoli.common.utils.Mail;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {


    @Bean
//
    @ConditionalOnProperty(prefix="spring.mail",name="username")
    public Mail mail(JavaMailSender mailSender, MailProperties mailProperties) {
        return new Mail(mailSender,mailProperties);
    }
}
