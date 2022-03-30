package com.javagang.rdcoursemanagementplatform.helper;

import com.javagang.rdcoursemanagementplatform.constant.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.jsonwebtoken.Jwt;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String getBearerTokenHeader() {
        String t = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        return t.substring(Constants.BEGIN_INDEX);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header(AUTHORIZATION_HEADER, getBearerTokenHeader());

    }
}
