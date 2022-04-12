package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.dto.ForgotPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.ResetPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import com.javagang.rdcoursemanagementplatform.utility.MailUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
class UserPasswordTest {

  @Mock
  private UserRepository userRepository;
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private JwtTokenUtil jwtUtil;
  @Mock private MailUtility mailUtility;
  @InjectMocks
  private UserService userService;

  @Test()
  public void userNotFoundException() {
    when(userRepository.findByMail("email+"))
            .thenThrow(new UserNotFoundException("User not Found by Email"));
    assertThrows(
            UserNotFoundException.class,
            () -> userService.sendMailToResetPassword(initForgotPasswordDTO("email+")));
  }

    @Test
    void sendMailToResetPassword() {
      var user = initUser();
      var encoded = passwordEncoder.encode(user.getPassword());
      var token = jwtUtil.createJwtToken(user.getMail());
      when(userRepository.findByMail(user.getMail())).thenReturn(Optional.of(user));
      when(passwordEncoder.encode(user.getPassword())).thenReturn(encoded);
      when(jwtUtil.createJwtToken(user.getMail())).thenReturn(token);
      userService.sendMailToResetPassword(initForgotPasswordDTO(user.getMail()));
      verify(passwordEncoder, times(1)).encode(user.getPassword());
      verify(mailUtility).resetPasswordMail(user.getMail(), token);
    }

    @Test
    void resetPassword() {
      var user = initUser();
      when(userRepository.findByMail(user.getMail())).thenReturn(Optional.of(user));
      when(userRepository.save(user)).thenReturn(user);
      var token = "JwtTokenByName";
      var reset = initResetPasswordDTO("newPassword");
      var encoded = new BCryptPasswordEncoder().encode(reset.getPassword());
      when(passwordEncoder.encode(reset.getPassword())).thenReturn(encoded);
      when(jwtUtil.verifyAndGetMail(token)).thenReturn(user.getMail());
      userService.resetPassword(reset, token);
      verify(userRepository).findByMail(user.getMail());
    }

  private User initUser() {
    var user = new User();
    user.setMail("yeritsyan.arsen@gmail.com");
    user.setPassword("strong");
    user.setPictureId("file//");
    user.setFirstName("FirstN");
    user.setLastName("LastN");
    return user;
  }

  private ResetPasswordDTO initResetPasswordDTO(String password) {
    ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
    resetPasswordDTO.setPassword(password);
    resetPasswordDTO.setConfirm_password(password);
    return resetPasswordDTO;
  }

  private ForgotPasswordDTO initForgotPasswordDTO(String email) {
    ForgotPasswordDTO forgotPasswordDTO = new ForgotPasswordDTO();
    forgotPasswordDTO.setEmail(email);
    return forgotPasswordDTO;
  }
}