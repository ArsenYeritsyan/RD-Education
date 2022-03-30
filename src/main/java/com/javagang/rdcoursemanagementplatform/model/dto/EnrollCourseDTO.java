package com.javagang.rdcoursemanagementplatform.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EnrollCourseDTO {
   private List<UUID> Ids;
}
