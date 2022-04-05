package com.javagang.rdcoursemanagementplatform.validation.password;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
  private static final String PASSWORD_REGEX =
      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$";
  //  ^                                 # start of line
  //  (?=.*[0-9])                       # positive lookahead, digit [0-9]
  //  (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
  //  (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
  //  (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the given special characters
  //  .                                 # matches anything
  //  {8,20}                            # length at least 8 characters and maximum of 20 characters
  //  $                                 # end of line
  private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    if (password != null) {
      var matcher = passwordPattern.matcher(password);
      return matcher.matches();
    }
    return true;
  }
}
