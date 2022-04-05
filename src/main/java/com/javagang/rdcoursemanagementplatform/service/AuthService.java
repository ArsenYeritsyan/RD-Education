package com.javagang.rdcoursemanagementplatform.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import com.javagang.rdcoursemanagementplatform.utility.JwtUtility;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.repository.RoleRepository;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.exception.ResourceNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.dto.request.SignUpByInvitationDto;
import com.javagang.rdcoursemanagementplatform.model.dto.registrationentity.RegistrationEntityFactory;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

  private final JwtUtility jwtUtility;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final RegistrationEntityFactory entityFactory;

  public UUID registerByInvitation(
          final SignUpByInvitationDto invitationDto, final String token) {

    try {
      var claims = jwtUtility.extractClaims(token);
      var roleType = getRoleType(claims);
      var entity = entityFactory.getEntity(roleType);
      var preparedEntity =
          entity.prepareRegistrationEntity(invitationDto, getRole(roleType), claims);
      return userRepository.save(preparedEntity).getId();
    } catch (JwtException | ResourceNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private RoleType getRoleType(Claims claims) {
    try {
      var tokenRoleType = (String) claims.get(Constants.INVITATION_EMAIL_ROLE_TYPE_KEY);
      return RoleType.valueOf(tokenRoleType);
    } catch (Exception e) {
      throw new ResourceNotFoundException("Invalid token: provided roleType doesn't exist");
    }
  }

  private Role getRole(RoleType roleType) {
    return roleRepository
        .findByRoleType(roleType)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("Role with type %s doesn't exist", roleType)));
  }
}
