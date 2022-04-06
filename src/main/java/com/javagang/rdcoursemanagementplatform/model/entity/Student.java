package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;
import java.time.LocalDate;

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

  @ManyToOne
  @JoinColumn(name = "faculty_id", referencedColumnName = "id")
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
