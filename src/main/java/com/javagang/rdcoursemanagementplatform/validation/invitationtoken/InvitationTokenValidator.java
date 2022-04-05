package com.javagang.rdcoursemanagementplatform.validation.invitationtoken;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.javagang.rdcoursemanagementplatform.utility.JwtUtility;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class InvitationTokenValidator implements ConstraintValidator<InvitationToken, String> {
  private final JwtUtility jwtUtility;
  private final UserRepository userRepository;

  @Override
  public boolean isValid(String token, ConstraintValidatorContext context) {
    try {
      var claims = jwtUtility.extractClaims(token);
      var email = claims.get(Constants.INVITATION_EMAIL_MAIL_KEY);
      return email != null && isValidRoleType(claims) && isUniqueUsername(email.toString());
    } catch (Exception e) {
      log.error("InvitationTokenValidator failed: " + e.getMessage());
      return false;
    }
  }

  private boolean isValidRoleType(Claims claims) {
    try {
      RoleType.valueOf((String) claims.get(Constants.INVITATION_EMAIL_ROLE_TYPE_KEY));
      return true;
    } catch (IllegalArgumentException e) {
      log.error("InvitationTokenValidator failed: " + e.getMessage());
      return false;
    }
  }

  private boolean isUniqueUsername(String mail) {
    return !userRepository.existsUserByMail(mail);
  }
}
