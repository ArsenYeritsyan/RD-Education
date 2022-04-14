package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;
import java.util.Date;
import java.util.UUID;
import java.util.Objects;
import java.time.LocalDateTime;
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
  @Column(name = "id", updatable = false, columnDefinition = "CHAR(36)")
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id", referencedColumnName = "id")
  @JsonBackReference
  private Faculty faculty;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private Set<Homework> homeworks;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Student> students;

  @Column(name = "startTime")
  private Date startTime;

  @Column(name = "startDate", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "endDate", nullable = false)
  private LocalDateTime endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private Professor professor;

  public Course(String name, Faculty faculty) {
    this.name = name;
    this.faculty = faculty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(id, course.id) && Objects.equals(name, course.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
