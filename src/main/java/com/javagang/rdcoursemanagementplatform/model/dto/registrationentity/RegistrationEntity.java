package com.javagang.rdcoursemanagementplatform.model.dto.registrationentity;

import io.jsonwebtoken.Claims;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.model.dto.request.SignUpByInvitationDto;

public interface RegistrationEntity<T extends User> {

  RoleType getType();

  T prepareRegistrationEntity(SignUpByInvitationDto dto, Role role, Claims claims);
}
