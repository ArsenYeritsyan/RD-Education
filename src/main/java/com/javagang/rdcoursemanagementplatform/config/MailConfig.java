package com.javagang.rdcoursemanagementplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class MailConfig {
  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.username}")
  private String account;

  @Value("${spring.mail.password}")
  private String password;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private String auth;

  @Value("${spring.mail.properties.mail.smtp.port}")
  private int port;

  @Value("${spring.mail.properties.mail.transport.protocol}")
  private String protocol;

  @Bean
  public JavaMailSender javaMailSender() {

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    Properties props = mailSender.getJavaMailProperties();
    props.setProperty("mail.transport.protocol", protocol);
    props.setProperty("mail.smtp.auth", auth);
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.connection-timeout", "10000");
    props.setProperty("mail.debug", "true");
    props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    Session session = Session.getInstance(
            props,
            new javax.mail.Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, password);
              }
            });
    mailSender.setJavaMailProperties(props);
    mailSender.setHost(host);
    mailSender.setPort(port);
    mailSender.setUsername(account);
    mailSender.setPassword(password);
    mailSender.setDefaultEncoding("UTF-8");
    return mailSender;
  }
}
