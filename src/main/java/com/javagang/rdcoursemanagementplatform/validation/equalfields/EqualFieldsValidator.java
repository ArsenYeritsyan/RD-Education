package com.javagang.rdcoursemanagementplatform.validation.equalfields;

import lombok.extern.slf4j.Slf4j;
import com.javagang.rdcoursemanagementplatform.utility.Utility;
import com.javagang.rdcoursemanagementplatform.exception.FieldValueReadFailedException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {

  private String baseField;
  private String matchField;

  @Override
  public void initialize(EqualFields constraint) {
    baseField = constraint.baseField();
    matchField = constraint.matchField();
  }

  @Override
  public boolean isValid(Object object, ConstraintValidatorContext context) {
    var areEqual = false;
    try {
      var baseFieldValue = Utility.getFieldValue(object, baseField);
      var matchFieldValue = Utility.getFieldValue(object, matchField);
      if (baseFieldValue == null) {
        areEqual = matchFieldValue == null;
      } else {
        areEqual = baseFieldValue.equals(matchFieldValue);
      }
    } catch (FieldValueReadFailedException e) {
      log.error(e.getMessage());
    }
    return areEqual;
  }
}
