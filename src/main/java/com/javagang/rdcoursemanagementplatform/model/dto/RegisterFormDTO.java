package com.javagang.rdcoursemanagementplatform.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterFormDTO {
    private String password;
    private String username;
    private String mail;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
