package com.javagang.rdcoursemanagementplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "faculty_name", nullable = false, unique = true)
  private String facultyName;

  @OneToMany(mappedBy = "faculty")
  private Set<Professor> professors = new HashSet<>();

  @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
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
        && Objects.equals(professors, faculty.professors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, facultyName, professors);
  }
}
