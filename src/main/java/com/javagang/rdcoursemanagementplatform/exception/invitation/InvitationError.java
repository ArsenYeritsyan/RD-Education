package com.javagang.rdcoursemanagementplatform.exception.invitation;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
public class InvitationError {
  private String recipientEmail;
  private String message;
  private LocalDateTime localDateTime;
}
