package com.javagang.rdcoursemanagementplatform.controller;

import com.javagang.rdcoursemanagementplatform.model.dto.ForgotPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.ResetPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.UserDTO;
import com.javagang.rdcoursemanagementplatform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/forgot_password/")
  @Operation(summary = "Send a link with token to reset a password")
  public ResponseEntity<Void> sendMailToResetPassword(@RequestBody ForgotPasswordDTO forgotPassword) {
    userService.sendMailToResetPassword(forgotPassword);
    return ResponseEntity.accepted().build();
  }

  @PutMapping("/reset_password/{token}")
  @Operation(summary = "Check the token in link of the mail and reset password")
  public ResponseEntity<Void> resetUserPassword(
      @RequestBody ResetPasswordDTO resetPassword, @PathVariable("token") String token) {
    userService.resetPassword(resetPassword, token);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/{email}")
  @Operation(summary = "get user by Email")
  public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
    return ResponseEntity.ok(userService.getUserByEmail(email));
  }
}