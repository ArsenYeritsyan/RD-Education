package com.javagang.rdcoursemanagementplatform.model.dto.registrationentity;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.entity.Student;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.model.dto.request.SignUpByInvitationDto;

import java.util.Set;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class StudentRegistrationEntity implements RegistrationEntity<Student> {
  private final PasswordEncoder bCryptPasswordEncoder;

  @Override
  public RoleType getType() {
    return RoleType.STUDENT;
  }

  @Override
  public Student prepareRegistrationEntity(
          SignUpByInvitationDto dto, Role role, Claims claims) {
    Student student = new Student();
    student.setMail((String) claims.get(Constants.INVITATION_EMAIL_MAIL_KEY));
    student.setRoles(Set.of(role));
    // student.setFaculty();
    student.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
    student.setFirstName(dto.getFirstName());
    student.setLastName(dto.getLastName());
    student.setDob(dto.getDob());
    student.setPictureId(dto.getPictureId());
    student.setStartDate(
        LocalDate.parse((String) claims.get(Constants.INVITATION_EMAIL_START_DATE_KEY)));
    // student.setVerified(true);
    return student;
  }
}
