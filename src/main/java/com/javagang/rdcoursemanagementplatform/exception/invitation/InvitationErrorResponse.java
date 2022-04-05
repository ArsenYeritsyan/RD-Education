package com.javagang.rdcoursemanagementplatform.exception.invitation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class InvitationErrorResponse {
  private List<InvitationError> errors = new ArrayList<>();
}
