package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

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
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", columnDefinition = "CHAR(36)")
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "faculty_id", nullable = false)
  private Faculty faculty;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private Set<Homework> homeworks;

  @ManyToMany(fetch = FetchType.LAZY)
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
