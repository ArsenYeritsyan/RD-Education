package com.javagang.rdcoursemanagementplatform.validation.uniqueusername;

import lombok.RequiredArgsConstructor;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
  private final UserRepository repository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
    return !repository.existsUserByMail(email);
  }
}
