package com.course.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String s) {
        super(s + " not found");
    }
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
}


