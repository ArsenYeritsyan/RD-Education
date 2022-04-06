package com.javagang.rdcoursemanagementplatform.validation.equalfields;

import javax.validation.Payload;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EqualFieldsValidator.class})
public @interface EqualFields {
  String message() default "{com.javagang.EqualFields.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String baseField();

  String matchField();
}
