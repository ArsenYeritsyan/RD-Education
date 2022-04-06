package com.javagang.rdcoursemanagementplatform.validation.password;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
  String message() default "{com.javagang.Password.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
