package com.javagang.rdcoursemanagementplatform.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private UUID id;

  @Column(name = "mail", unique = true, nullable = false, length = 100)
  private String mail;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Column(name = "picture_id")
  private String pictureId;

  @Column(name = "birth_year", nullable = false)
  private LocalDate birthYear;

  //  @ManyToOne
  //  @JoinColumn(name = "faculty_id", nullable = false)
  //  private Faculty faculty;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "student_group", nullable = false, length = 100)
  private String group;

  //  @ManyToMany(cascade = {CascadeType.ALL})
  //  @JoinTable(
  //      name = "Student_Courses",
  //      joinColumns = {@JoinColumn(name = "student_id")},
  //      inverseJoinColumns = {@JoinColumn(name = "course_id")})
  //  private Set<Course> courses = new HashSet<>();
}
