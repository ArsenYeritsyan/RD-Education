package com.javagang.rdcoursemanagementplatform.constant;

public final class Constants {
  public static final int HOMEWORK_MAX = 6;
  public static final int RESET_TOKEN_EXPIRATION = 60 * 24;
  public static final int BEGIN_INDEX = 7;
  public static final String BEARER = "Bearer ";
  public static final String RESET_PASS_LINK = "/api/v1/users/reset_password/";
  public static final String TOKEN_VERIFICATION_LINK = "/api/v1/users/verify?token=";
  public static final String CLAIM_BY = "email";
  public static final long LOGIN_TOKEN_VALIDITY = 5L * 60 * 60 * 1000;
  public static final long INVITATION_TOKEN_VALIDITY = 2L * 24 * 60 * 60 * 1000;
  public static final String INVITATION_EMAIL_ROLE_TYPE_KEY = "roleType";
  public static final String INVITATION_EMAIL_MAIL_KEY = "mail";
  public static final String INVITATION_EMAIL_FACULTY_NAME_KEY = "facultyName";
  public static final String INVITATION_EMAIL_START_DATE_KEY = "startDate";
  public static final String INVITATION_EMAIL_URL_KEY = "url";
  public static final String INVITATION_EMAIL_TEMPLATE_PATH = "/email/invitation.ftl";
  public static final boolean IS_MIME_MESSAGE_TEXT_HTML = true;
  public static final String PATH_TO_USERS = "/api/v1/users/";
  public static final String INVITATION_URL = "/api/v1/auth/signUpByInvitation?invitationToken=";
  public static final String INVITATION_EMAIL_SUBJECT = "JAVAGANG | Special Invitation";
}
