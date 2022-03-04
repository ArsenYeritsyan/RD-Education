package com.javagang.rdcoursemanagementplatform.model.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Table(name = "professor")
public class Professor extends User {

  //  @ManyToOne
  //  @JoinColumn(name = "faculty_id", nullable = false)
  //  private Faculty faculty;

  @Column(name = "is_verified", nullable = false, columnDefinition = "tinyint(1) default false")
  private boolean isVerified;

  public Professor() {}

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
