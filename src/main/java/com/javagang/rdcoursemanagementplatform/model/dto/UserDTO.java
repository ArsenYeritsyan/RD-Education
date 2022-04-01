package com.javagang.rdcoursemanagementplatform.model.dto;

import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private Set<Role> roles = new HashSet<>();
    private String mail;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String pictureId;
}
