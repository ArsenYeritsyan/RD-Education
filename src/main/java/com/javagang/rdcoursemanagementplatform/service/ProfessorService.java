package com.javagang.rdcoursemanagementplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;
import com.javagang.rdcoursemanagementplatform.model.entity.Professor;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfessorService {
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  public void assign(UUID professorId, UUID courseId) {
    var user =
        userRepository
            .findById(professorId)
            .orElseThrow(() -> new UserNotFoundException("Invalid user-id"));
    if (user.getRoles().stream().noneMatch(r -> r.getRoleType() == RoleType.PROFESSOR)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid professor-id");
    }
    var course =
        courseRepository
            .findById(courseId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid course-id"));
    if (course.getProfessor() != null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Course already assigned to a professor");
    }
    course.setProfessor((Professor) user);
    courseRepository.save(course);
  }
}
