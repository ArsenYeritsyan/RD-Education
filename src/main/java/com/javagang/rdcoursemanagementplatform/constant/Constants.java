package com.javagang.rdcoursemanagementplatform.constant;

public final class Constants {
  public static final int HOMEWORK_MAX = 6;
  public static final int RESET_TOKEN_EXPIRATION = 60 * 24;
  public static final int BEGIN_INDEX = 7;
  public static final String BEARER = "Bearer ";
  public static final String RESET_PASS_LINK = "/api/v1/users/reset_password/";
  public static final String TOKEN_VERIFICATION_LINK ="/api/v1/users/verify?token=";
  public static final String CLAIM_BY="email";
}
