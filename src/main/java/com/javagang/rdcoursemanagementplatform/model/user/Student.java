package com.javagang.rdcoursemanagementplatform.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends User {

  //  @ManyToOne
  //  @JoinColumn(name = "faculty_id", nullable = false)
  //  private Faculty faculty;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "grad_date", nullable = false)
  private LocalDate gradDate;

  //  @ManyToMany(cascade = {CascadeType.ALL})
  //  @JoinTable(
  //      name = "students_courses",
  //      joinColumns = {@JoinColumn(name = "student_id")},
  //      inverseJoinColumns = {@JoinColumn(name = "course_id")})
  //  private Set<Course> courses = new HashSet<>();

  public Student() {}

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
      LocalDate gradDate) {
    super(password, mail, firstName, lastName, dob, pictureId);
    this.startDate = startDate;
    this.gradDate = gradDate;
  }
}
