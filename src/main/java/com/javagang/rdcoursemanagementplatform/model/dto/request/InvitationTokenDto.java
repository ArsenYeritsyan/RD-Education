package com.javagang.rdcoursemanagementplatform.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.validation.uniqueusername.UniqueUsername;
import com.javagang.rdcoursemanagementplatform.validation.roledependantfields.RoleDependantField;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RoleDependantField(field = "roleType", value = "ROLE_STUDENT", required = "facultyName")
@RoleDependantField(field = "roleType", value = "ROLE_STUDENT", required = "startDate")
@RoleDependantField(field = "roleType", value = "ROLE_PROFESSOR", required = "facultyName")
public class InvitationTokenDto {
  @NotBlank(message = "{com.javagang.EmailNotEmpty.message}")
  @Email
  @UniqueUsername
  @JsonProperty("mail")
  private String mail;

  @NotNull(message = "{com.javagang.RoleTypeNotNull.message}")
  @JsonProperty("roleType")
  private RoleType roleType;

  @JsonProperty("facultyName")
  private String facultyName;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("startDate")
  private LocalDate startDate;
}
