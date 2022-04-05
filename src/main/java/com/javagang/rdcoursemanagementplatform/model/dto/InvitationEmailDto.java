package com.javagang.rdcoursemanagementplatform.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@SuperBuilder
public class InvitationEmailDto extends EmailDto {
  private Map<String, Object> templateData;
}
