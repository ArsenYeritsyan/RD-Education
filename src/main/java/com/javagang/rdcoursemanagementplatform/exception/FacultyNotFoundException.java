package com.javagang.rdcoursemanagementplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FacultyNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public FacultyNotFoundException(String message) {
    super("Not Found :" + message);
  }
}
