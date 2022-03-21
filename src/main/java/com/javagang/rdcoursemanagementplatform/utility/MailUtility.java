package com.javagang.rdcoursemanagementplatform.utility;

import com.javagang.rdcoursemanagementplatform.constant.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
@RequiredArgsConstructor
public class MailUtility {

  private final JavaMailSenderImpl sender;
  private final ServletContext context;

  @Async
  public void resetPasswordMail(String email, String jwtToken) {
    SimpleMailMessage message = new SimpleMailMessage();
    String link = context.getContextPath() + Constants.RESET_PASS_LINK + jwtToken;
    message.setTo(email);
    message.setSubject("Password Reset Request");
    message.setText("To reset your password, click the link	" + link);
    sender.send(message);
  }

//  public void sendMail(String email, String jwtToken) {
//    SimpleMailMessage message = new SimpleMailMessage();
//    String link = (context.getContextPath() + Constants.TOKEN_VERIFICATION_LINK + jwtToken);
//    message.setTo(email);
//    message.setSubject("Account verification mail");
//    message.setText(
//        "Registration Successful to activate your account click on this link   " + link);
//    sender.send(message);
//    }
}