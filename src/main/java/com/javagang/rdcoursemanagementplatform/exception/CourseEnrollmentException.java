package com.javagang.rdcoursemanagementplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CourseEnrollmentException extends RuntimeException {
    public CourseEnrollmentException(String message) {
        super(message);
    }
}
