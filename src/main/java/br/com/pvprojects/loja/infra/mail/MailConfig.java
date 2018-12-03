package br.com.pvprojects.loja.infra.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Value("${loja.mail.host}")
    private String host;

    @Value("${loja.mail.port}")
    private Integer port;

    @Value("${loja.mail.username}")
    private String username;

    @Value("${loja.mail.password}")
    private String password;


    @Bean
    public JavaMailSender javaMailSender() {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);
        props.put("mail.smtp.ssl.trust", host);

        JavaMailSenderImpl impl = new JavaMailSenderImpl();
        impl.setJavaMailProperties(props);
        impl.setHost(host);
        impl.setPort(port);
        impl.setUsername(username);
        impl.setPassword(password);

        return impl;
    }
}