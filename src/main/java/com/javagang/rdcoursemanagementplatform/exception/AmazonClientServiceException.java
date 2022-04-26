package com.javagang.rdcoursemanagementplatform.exception;

public class AmazonClientServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AmazonClientServiceException(String errorMessage) {
        super(errorMessage);
    }
}
