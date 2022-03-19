package com.javagang.rdcoursemanagementplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForgotPasswordDTO {
    @JsonProperty("email")
    private String email;
}
