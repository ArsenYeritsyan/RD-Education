package com.javagang.rdcoursemanagementplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FacultyDTO {
  @JsonProperty("id")
  private UUID id;

  @JsonProperty("name")
  private String facultyName;

  @JsonProperty("courses")
  private Set<CourseDTO> courses;
  //  private Set<ProfessorDto> professors;
}
