package com.javagang.rdcoursemanagementplatform.model.dto.request;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javagang.rdcoursemanagementplatform.validation.password.Password;
import com.javagang.rdcoursemanagementplatform.validation.equalfields.EqualFields;

import java.time.LocalDate;
import javax.validation.constraints.Past;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Data
@EqualFields(baseField = "password", matchField = "confirmedPassword")
public class SignUpByInvitationDto {

  @NotBlank(message = "{com.javagang.PasswordNotEmpty.message}")
  @Password
  @JsonProperty("password")
  private String password;

  @JsonProperty("confirmedPassword")
  private String confirmedPassword;

  @NotBlank(message = "{com.javagang.FirstNameNotEmpty.message}")
  @JsonProperty("firstName")
  private String firstName;

  @NotBlank(message = "{com.javagang.LastNameNotEmpty.message}")
  @JsonProperty("lastName")
  private String lastName;

  @NotNull(message = "{com.javagang.Dob.message}")
  @Past
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("dob")
  private LocalDate dob;

  @JsonProperty("pictureId")
  private String pictureId;
}
