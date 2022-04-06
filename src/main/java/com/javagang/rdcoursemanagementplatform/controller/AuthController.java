package com.javagang.rdcoursemanagementplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.javagang.rdcoursemanagementplatform.utility.Utility;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.service.AuthService;
import com.javagang.rdcoursemanagementplatform.model.dto.request.SignUpByInvitationDto;
import com.javagang.rdcoursemanagementplatform.validation.invitationtoken.InvitationToken;

import java.net.URI;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping(value = "/signUpByInvitation")
  public ResponseEntity<Void> signUpByInvitation(
      @RequestBody @Valid final SignUpByInvitationDto invitationRegistrationDto,
      @RequestParam("invitationToken") @InvitationToken final String invitationToken,
      HttpServletRequest req) {
    var id = authService.registerByInvitation(invitationRegistrationDto, invitationToken);
    var location = URI.create(Utility.getBaseUrl(req) + Constants.PATH_TO_USERS + id);
    return ResponseEntity.created(location).build();
  }
}
