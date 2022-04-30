package com.javagang.rdcoursemanagementplatform.exception;

public class AmazonClientServiceException extends RuntimeException {
    public AmazonClientServiceException(String errorMessage) {
        super(errorMessage);
    }
}
