package com.javagang.rdcoursemanagementplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CourseDTO {
  @JsonProperty("id")
  private UUID id;

  @JsonProperty("name")
  private String name;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("date")
  private Date startTime;

  @JsonProperty("faculty")
  private FacultyDTO faculty;

  //  private Set<HomeworkDto> homeworks;
  //  private Set<StudentDto> students;
}
