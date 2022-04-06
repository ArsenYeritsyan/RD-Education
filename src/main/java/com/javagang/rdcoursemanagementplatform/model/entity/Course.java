package com.javagang.rdcoursemanagementplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "startDate", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "endDate", nullable = false)
  private LocalDateTime endDate;

  @ManyToOne
  @JoinColumn(name = "faculty_id", nullable = false)
  private Faculty faculty;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private Set<Homework> homeworks;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
  private Set<Student> students;

  public Course(String name, Faculty faculty) {
    this.name = name;
    this.faculty = faculty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(id, course.id)
        && Objects.equals(name, course.name)
        && Objects.equals(faculty, course.faculty)
        && Objects.equals(homeworks, course.homeworks)
        && Objects.equals(students, course.students);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, faculty, homeworks, students);
  }

}
