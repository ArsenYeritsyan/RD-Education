package com.javagang.rdcoursemanagementplatform.validation.invitationtoken;

import javax.validation.Payload;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvitationTokenValidator.class)
public @interface InvitationToken {
  String message() default "{com.javagang.InvitationToken.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
