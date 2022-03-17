package com.javagang.rdcoursemanagementplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javagang.rdcoursemanagementplatform.validation.PasswordsEqualConstraint;
import lombok.Data;

@Data
@PasswordsEqualConstraint(message = "Please enter the same passwords")
public class ResetPasswordDTO {
    @JsonProperty("password")
    private String password;
    @JsonProperty("confirm_password")
    private String confirm_password;
}
