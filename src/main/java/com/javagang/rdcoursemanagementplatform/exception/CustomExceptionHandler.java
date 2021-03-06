package com.javagang.rdcoursemanagementplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorMessage> userExceptionHandler(
      UserNotFoundException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FacultyNotFoundException.class)
  public ResponseEntity<ErrorMessage> facultyExceptionHandler(
      FacultyNotFoundException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<ErrorMessage> courseExceptionHandler(
      CourseNotFoundException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AmazonClientServiceException.class)
  public ResponseEntity<ErrorMessage> amazonExceptionHandler(
          AmazonClientServiceException ex, WebRequest request) {
    ErrorMessage message =
            new ErrorMessage(
                    HttpStatus.NOT_FOUND.value(),
                    new Date(),
                    ex.getMessage(),
                    request.getDescription(false));
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

}
