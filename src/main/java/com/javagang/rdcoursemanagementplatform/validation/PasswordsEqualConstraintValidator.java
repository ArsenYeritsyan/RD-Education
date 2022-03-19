package com.javagang.rdcoursemanagementplatform.validation;

import com.javagang.rdcoursemanagementplatform.model.dto.ResetPasswordDTO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class PasswordsEqualConstraintValidator
    implements ConstraintValidator<PasswordsEqualConstraint, ResetPasswordDTO> {

  @Override
  public void initialize(PasswordsEqualConstraint arg0) {}

  @Override
  public boolean isValid(ResetPasswordDTO pass, ConstraintValidatorContext context) {

    if (pass.getPassword().contains(" ")
        || pass.getConfirm_password().contains(" ")
        || pass.getPassword() == null
        || pass.getConfirm_password() == null) throw new NullPointerException("Passwords are not valid");
    return pass.getPassword().equals(pass.getConfirm_password());
  }
}
