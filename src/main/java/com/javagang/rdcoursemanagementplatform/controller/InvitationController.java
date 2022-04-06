package com.javagang.rdcoursemanagementplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.javagang.rdcoursemanagementplatform.utility.Utility;
import org.springframework.security.access.prepost.PreAuthorize;
import com.javagang.rdcoursemanagementplatform.service.InvitationService;
import com.javagang.rdcoursemanagementplatform.model.dto.request.InvitationTokenDto;

import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1")
public class InvitationController {

  private final InvitationService service;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(value = "/invite")
  public ResponseEntity<?> invite(
      @RequestBody @NotEmpty(message = "Invitation-Set cannot be empty")
          final Set<@NotNull(message = "Invitation cannot be null") @Valid InvitationTokenDto>
              invitations,
      HttpServletRequest req) {
    return service.sendInvitations(invitations, Utility.getBaseUrl(req));
  }
}
