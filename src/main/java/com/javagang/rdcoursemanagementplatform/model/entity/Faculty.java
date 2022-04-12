package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
  @Column(name = "id", updatable = false, columnDefinition = "CHAR(36)")
  private UUID id;

  @Column(name = "faculty_name", nullable = false, unique = true)
  private String facultyName;

  @OneToMany(mappedBy = "faculty")
  private Set<Professor> professors = new HashSet<>();

  @OneToMany(mappedBy = "faculty",fetch = FetchType.LAZY)
  private Set<Course> courses = new HashSet<>();

  @OneToMany(mappedBy = "faculty")
  private Set<Student> students = new HashSet<>();

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
    return Objects.equals(id, faculty.id) && facultyName.equals(faculty.facultyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, facultyName);
  }
}
