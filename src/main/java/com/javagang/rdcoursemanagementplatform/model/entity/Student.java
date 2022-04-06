package com.javagang.rdcoursemanagementplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends User {
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "start_date")
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "graduation_date")
  private LocalDate graduationDate;

  @ManyToMany(cascade = {CascadeType.PERSIST})
  @JoinTable(
      name = "student_course",
      joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
  private Set<Course> courses = new HashSet<>();

  @OneToMany(mappedBy = "student")
  private Set<Homework> homeworks;

  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      optional = false)
  @JoinColumn(name = "faculty_id", nullable = false, unique = true)
  private Faculty faculty;

  public Student(
      String password,
      String mail,
      String firstName,
      String lastName,
      LocalDate dob,
      String pictureId) {
    super(password, mail, firstName, lastName, dob, pictureId);
  }

  public Student(
      String password,
      String mail,
      String firstName,
      String lastName,
      LocalDate dob,
      String pictureId,
      LocalDate startDate,
      LocalDate graduationDate) {
    super(password, mail, firstName, lastName, dob, pictureId);
    this.startDate = startDate;
    this.graduationDate = graduationDate;
  }
}
