package com.javagang.rdcoursemanagementplatform.utility;

import com.javagang.rdcoursemanagementplatform.exception.FieldValueReadFailedException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class Utility {
  private Utility() {}

  public static String getBaseUrl(HttpServletRequest request) {
    return request
        .getRequestURL()
        .substring(0, request.getRequestURL().length() - request.getRequestURI().length())
        .concat(request.getContextPath());
  }

  public static Object getFieldValue(Object object, String fieldName)
      throws FieldValueReadFailedException {
    var clazz = object.getClass();
    try {
      var field = getField(clazz, fieldName);
      field.setAccessible(true);
      return field.get(object);
    } catch (Exception e) {
      throw new FieldValueReadFailedException(
          "Failed to read the value of the field: " + fieldName);
    }
  }

  private static Field getField(Class<?> clazz, String fieldName) {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      if (clazz.getSuperclass() != null) {
        return getField(clazz.getSuperclass(), fieldName);
      } else {
        throw new FieldValueReadFailedException(
            String.format("The property by name %s doesn't exist", fieldName));
      }
    }
  }
}
