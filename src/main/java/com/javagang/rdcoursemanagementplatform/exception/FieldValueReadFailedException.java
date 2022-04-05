package com.javagang.rdcoursemanagementplatform.exception;

public class FieldValueReadFailedException extends RuntimeException{
    public FieldValueReadFailedException() {
        super();
    }

    public FieldValueReadFailedException(String message) {
        super(message);
    }

    public FieldValueReadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
