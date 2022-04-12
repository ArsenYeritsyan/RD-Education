package com.javagang.rdcoursemanagementplatform.validation.roledependantfields;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.javagang.rdcoursemanagementplatform.utility.Utility;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.repository.FacultyRepository;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import java.time.format.DateTimeParseException;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class RoleDependantFieldsValidator
    implements ConstraintValidator<RoleDependantField, Object> {
  private String roleType;
  private String roleTypeValue;
  private String requiredField;

  private final FacultyRepository facultyRepository;

  @Override
  public void initialize(RoleDependantField annotation) {
    this.roleType = annotation.field();
    this.roleTypeValue = annotation.value();
    this.requiredField = annotation.required();
  }

  @Override
  public boolean isValid(Object dto, ConstraintValidatorContext context) {
    boolean hasValidValue = true;
    try {
      var actualRoleType = (RoleType) Utility.getFieldValue(dto, roleType);
      if (actualRoleType == RoleType.valueOf(roleTypeValue)) {
        hasValidValue =
            isRequiredValueValid(Utility.getFieldValue(dto, requiredField), actualRoleType);
      }
    } catch (Exception e) {
      hasValidValue = false;
      log.error(String.format("RoleDependant check failed: %s", e.getMessage()));
    }
    return hasValidValue;
  }

  private boolean isRequiredValueValid(Object requiredValue, RoleType roleType) {
    if (requiredValue == null) {
      return false;
    }

    var isValueValid = true;
    if (roleType == RoleType.STUDENT) {
      isValueValid = isValidForStudentRoleType(requiredValue.toString());
    }
    if (roleType == RoleType.PROFESSOR) {
      isValueValid = isFacultyNameValid(requiredValue.toString());
    }
    return isValueValid;
  }

  private boolean isValidForStudentRoleType(String requiredFieldValue) {
    if (Constants.INVITATION_EMAIL_FACULTY_NAME_KEY.equals(requiredField)) {
      return isFacultyNameValid(requiredFieldValue);
    }
    if (Constants.INVITATION_EMAIL_START_DATE_KEY.equals(requiredField)) {
      return isValidLocalDate(requiredFieldValue);
    }
    return true;
  }

  private boolean isFacultyNameValid(String facultyName) {
    return facultyRepository.findByFacultyName(facultyName).isPresent();
  }

  private boolean isValidLocalDate(String startDate) {
    try {
      LocalDate.parse(startDate);
      return true;
    } catch (DateTimeParseException e) {
      log.error("RoleDependant check: Invalid 'startDate' format");
      return false;
    }
  }
}
