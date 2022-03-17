package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "faculty")
public class Faculty {
  @Id
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", columnDefinition = "CHAR(36)")
  private UUID id;

  @Column(name = "faculty_name", nullable = false, unique = true)
  private String facultyName;

  @OneToMany(mappedBy = "faculty")
  private Set<Professor> professors = new HashSet<>();

  @OneToMany(mappedBy = "faculty")
  private Set<Course> courses = new HashSet<>();

  public Faculty(String facultyName) {
    this.facultyName = facultyName;
  }

  @Override
  public String toString() {
    return "Faculty{" + "id=" + id + ", facultyName='" + facultyName + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Faculty faculty = (Faculty) o;
    return Objects.equals(id, faculty.id)
        && facultyName.equals(faculty.facultyName)
        && Objects.equals(professors, faculty.professors)
        && Objects.equals(courses, faculty.courses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, facultyName, professors, courses);
  }
}
