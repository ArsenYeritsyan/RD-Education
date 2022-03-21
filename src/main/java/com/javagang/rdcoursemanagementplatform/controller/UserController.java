package com.javagang.rdcoursemanagementplatform.controller;

import com.javagang.rdcoursemanagementplatform.model.dto.ForgotPasswordDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.ResetPasswordDTO;
import com.javagang.rdcoursemanagementplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/forgot_password/")
  public ResponseEntity<?> sendMailToResetPassword(@RequestBody ForgotPasswordDTO forgotPassword) {
    return ResponseEntity.ok(userService.sendMailToResetPassword(forgotPassword));
  }

  @PutMapping("/reset_password/{token}")
  public ResponseEntity<Void> resetUserPassword(@RequestBody ResetPasswordDTO resetPassword, @PathVariable("token") String token) {
    userService.resetPassword(resetPassword, token);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{email}")
  public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
    return ResponseEntity.ok(userService.getUserByEmail(email));
  }
}