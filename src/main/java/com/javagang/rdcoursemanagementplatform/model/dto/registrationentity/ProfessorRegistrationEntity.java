package com.javagang.rdcoursemanagementplatform.model.dto.registrationentity;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.entity.Professor;
import com.javagang.rdcoursemanagementplatform.repository.FacultyRepository;
import com.javagang.rdcoursemanagementplatform.exception.ResourceNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.dto.request.SignUpByInvitationDto;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class ProfessorRegistrationEntity implements RegistrationEntity<Professor> {
  private final FacultyRepository facultyRepository;
  private final PasswordEncoder bCryptPasswordEncoder;

  @Override
  public RoleType getType() {
    return RoleType.PROFESSOR;
  }

  @Override
  public Professor prepareRegistrationEntity(
          SignUpByInvitationDto dto, Role role, Claims claims) {
    Professor professor = new Professor();
    professor.setMail((String) claims.get(Constants.INVITATION_EMAIL_MAIL_KEY));
    professor.setRoles(Set.of(role));
    professor.setFaculty(getFaculty(claims));
    professor.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
    professor.setFirstName(dto.getFirstName());
    professor.setLastName(dto.getLastName());
    professor.setDob(dto.getDob());
    professor.setPictureId(dto.getPictureId());
    professor.setVerified(true);
    return professor;
  }

  private Faculty getFaculty(Claims claims) {
    var facultyName = (String) claims.get(Constants.INVITATION_EMAIL_FACULTY_NAME_KEY);
    return facultyRepository
        .findByFacultyName(facultyName)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("Faculty with name %s does not exist", facultyName)));
  }
}
