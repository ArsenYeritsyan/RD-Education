package com.javagang.rdcoursemanagementplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {
  @JsonProperty("id")
  private UUID id;

  @JsonProperty("mail")
  private String mail;

  @JsonProperty("password")
  private String password;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("dob")
  private LocalDate dob;

  @JsonProperty("pictureId")
  private String pictureId;
  
  @JsonProperty("roles")
  private Set<Role> roles = new HashSet<>();
}
