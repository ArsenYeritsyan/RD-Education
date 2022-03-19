package com.javagang.rdcoursemanagementplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterFormDTO {
    @JsonProperty("password")
    private String password;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dob")
    private LocalDate dob;
}
