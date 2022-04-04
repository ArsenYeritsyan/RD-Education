package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseEnrollmentException;
import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.helper.FeignClientInterceptor;
import com.javagang.rdcoursemanagementplatform.mapper.UserMapper;
import com.javagang.rdcoursemanagementplatform.model.dto.ForgotPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.ResetPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.UserDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import com.javagang.rdcoursemanagementplatform.utility.MailUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper mapper;
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

  public UserDTO getUserByEmail(String email) {
    User user = userRepository
            .findByMail(email)
            .orElseThrow(() -> new UserNotFoundException("User is not found.."));

    return mapper.userToUserDTO(user);
  }

}
