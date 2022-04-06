package com.javagang.rdcoursemanagementplatform.validation.roledependantfields;

import java.lang.annotation.*;
import javax.validation.Payload;
import javax.validation.Constraint;

@Repeatable(RoleDependantFields.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoleDependantFieldsValidator.class})
public @interface RoleDependantField {
  String message() default "{com.javagang.RoleDependant.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String field();

  String value();

  String required();
}
