package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.dto.ForgotPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.ResetPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import com.javagang.rdcoursemanagementplatform.utility.MailUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtUtil;
  private final MailUtility javaMailUtil;

  public String sendMailToResetPassword(ForgotPasswordDTO forgotPassword) {
    User user =
        userRepository
            .findByMail(forgotPassword.getEmail())
            .orElseThrow(() -> new UserNotFoundException("User Account is not found.."));
    String jwtToken = jwtUtil.createJwtToken(user.getMail());
    javaMailUtil.resetPasswordMail(user.getMail(), jwtToken);
    log.info("Email has been sent to your account");
    return user.getMail();
  }

  public void resetPassword(@Valid ResetPasswordDTO resetPassword, String token) {
    User user =
        userRepository
            .findByMail(jwtUtil.verifyAndGetMail(token))
            .orElseThrow(() -> new UserNotFoundException("User is not found.."));
    user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
    log.info("Password has been changed successfully");
  }

  public void sendEmailOnRegistrationSuccess (String email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject("Account verification mail");
    message.setText(
        "Registration Successful");
    }
}
