package com.javagang.rdcoursemanagementplatform.model.dto.registrationentity;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RegistrationEntityFactory {
  private final Map<RoleType, RegistrationEntity<? extends User>> entityMap;

  @Autowired
  private RegistrationEntityFactory(
      List<RegistrationEntity<? extends User>> entities) {
    entityMap =
        entities.stream()
            .collect(
                Collectors.toUnmodifiableMap(RegistrationEntity::getType, Function.identity()));
  }

  public RegistrationEntity<? extends User> getEntity(RoleType roleType) {
    return Optional.of(entityMap.get(roleType)).orElseThrow(IllegalArgumentException::new);
  }
}
