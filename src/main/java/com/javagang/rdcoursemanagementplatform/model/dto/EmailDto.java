package com.javagang.rdcoursemanagementplatform.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class EmailDto {
  private String from;
  private String subject;
  private String to;
}
