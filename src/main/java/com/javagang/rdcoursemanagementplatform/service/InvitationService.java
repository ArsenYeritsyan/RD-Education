package com.javagang.rdcoursemanagementplatform.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSendException;
import org.springframework.beans.factory.annotation.Value;
import com.javagang.rdcoursemanagementplatform.utility.Utility;
import com.javagang.rdcoursemanagementplatform.utility.JwtUtility;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.utility.MailUtility;
import com.javagang.rdcoursemanagementplatform.model.dto.InvitationEmailDto;
import com.javagang.rdcoursemanagementplatform.model.dto.request.InvitationTokenDto;
import com.javagang.rdcoursemanagementplatform.exception.invitation.InvitationError;
import com.javagang.rdcoursemanagementplatform.exception.FieldValueReadFailedException;
import com.javagang.rdcoursemanagementplatform.exception.invitation.InvitationErrorResponse;

import java.util.Map;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.lang.reflect.Field;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class InvitationService {

  private final JwtUtility jwtUtility;
  private final MailUtility mailUtility;

  @Value("${spring.mail.username}")
  private String senderEmail;

  public ResponseEntity<?> sendInvitations(Set<InvitationTokenDto> invitations, String baseUrl) {
    var pathToInvitation = baseUrl.concat(Constants.INVITATION_URL);
    var errorResponse = new InvitationErrorResponse();
    for (var invitation : invitations) {
      try {
        var token = createInvitationToken(invitation);
        var url = pathToInvitation.concat(token);
        var templateData = prepareTemplateData(invitation.getRoleType().name(), url);
        var recipientEmail = invitation.getMail();
        var emailDto = populateInvitation(recipientEmail, templateData);
        mailUtility.sendEmailUsingFtl(emailDto, Constants.INVITATION_EMAIL_TEMPLATE_PATH);
        log.info(String.format("Invitation email to %s sent successfully", recipientEmail));
      } catch (FieldValueReadFailedException | MailSendException e) {
        var errorItem =
            InvitationError.builder()
                .recipientEmail(invitation.getMail())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        errorResponse.getErrors().add(errorItem);
      }
    }
    if (errorResponse.getErrors().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private String createInvitationToken(InvitationTokenDto invitation)
      throws FieldValueReadFailedException {
    Map<String, Object> claims;
    try {
      claims = convertObjectToMap(invitation);
    } catch (Exception e) {
      throw new FieldValueReadFailedException(
          String.format(
              "Failed to convert the object: %s to the map! Message: %s",
              invitation, e.getMessage()));
    }
    var mail = invitation.getMail();
    return jwtUtility.generateToken(claims, mail, Constants.INVITATION_TOKEN_VALIDITY);
  }

  private Map<String, Object> convertObjectToMap(Object object) {
    return Stream.of(object.getClass().getDeclaredFields())
        .filter(field -> Utility.getFieldValue(object, field.getName()) != null)
        .collect(
            Collectors.toUnmodifiableMap(
                Field::getName,
                field -> convertLocalDateToString(Utility.getFieldValue(object, field.getName()))));
  }

  private Object convertLocalDateToString(Object value) {
    return value instanceof LocalDate ? value.toString() : value;
  }

  private Map<String, Object> prepareTemplateData(String role, String url) {
    return Map.of(
        Constants.INVITATION_EMAIL_ROLE_TYPE_KEY, role, Constants.INVITATION_EMAIL_URL_KEY, url);
  }

  private InvitationEmailDto populateInvitation(String mail, Map<String, Object> templateData) {
    return InvitationEmailDto.builder()
        .from(senderEmail)
        .subject(Constants.INVITATION_EMAIL_SUBJECT)
        .to(mail)
        .templateData(templateData)
        .build();
  }
}
