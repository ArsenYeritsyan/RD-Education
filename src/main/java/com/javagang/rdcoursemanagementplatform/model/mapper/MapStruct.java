package com.javagang.rdcoursemanagementplatform.model.mapper;

import com.javagang.rdcoursemanagementplatform.model.dto.CourseDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.FacultyDTO;
import com.javagang.rdcoursemanagementplatform.model.dto.UserDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MapStruct {

  CourseDTO courseToCourseDTO(Course course);

  @Mapping(target = "homeworks", ignore = true)
  @Mapping(target = "students", ignore = true)
  Course courseDTOToCourse(CourseDTO courseDTO);

  Set<CourseDTO> toSetDto(Set<Course> courses);

  FacultyDTO facultyToFacultyDTO(Faculty faculty);

  @Mapping(target = "professors", ignore = true)
  @Mapping(target = "students", ignore = true)
  Faculty facultyDTOToFaculty(FacultyDTO facultyDTO);

//  @Mapping(target = "roles", ignore = true)
  User toUser(UserDTO userDTO);

  UserDTO toUserDto(User user);
}
