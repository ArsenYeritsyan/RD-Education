package com.javagang.rdcoursemanagementplatform.exception;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExpiredTokenException extends ExpiredJwtException {
    private static final long serialVersionUID = 1L;

    public ExpiredTokenException(Header header, Claims claims, String message) {
        super(header, claims, message);
    }
}
