package com.javagang.rdcoursemanagementplatform.model.dto.registrationentity;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.model.dto.request.SignUpByInvitationDto;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class UserRegistrationEntity implements RegistrationEntity<User> {
  private final PasswordEncoder bCryptPasswordEncoder;

  @Override
  public RoleType getType() {
    return RoleType.ADMIN;
  }

  @Override
  public User prepareRegistrationEntity(
          SignUpByInvitationDto dto, Role role, Claims claims) {
    return User.builder()
        .mail((String) claims.get(Constants.INVITATION_EMAIL_MAIL_KEY))
        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
        .roles(Set.of(role))
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .dob(dto.getDob())
        .pictureId(dto.getPictureId())
        // .setVerified(true);
        .build();
  }
}
