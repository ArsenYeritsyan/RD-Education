package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "professor")
public class Professor extends User {
  @ManyToOne(cascade = {CascadeType.MERGE})
  @JoinColumn(name = "faculty_id", referencedColumnName = "id")
  private Faculty faculty;

  @Column(name = "is_verified", columnDefinition = "tinyint(1) default false")
  private boolean isVerified;

  @JsonManagedReference
  @OneToMany(mappedBy = "professor")
  private Set<Course> courses;

  public Professor(
      String password,
      String mail,
      String firstName,
      String lastName,
      LocalDate dob,
      String pictureId) {
    super(password, mail, firstName, lastName, dob, pictureId);
  }
}
