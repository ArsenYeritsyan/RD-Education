package com.javagang.rdcoursemanagementplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
  @Column(name = "id", updatable = false , columnDefinition = "CHAR(36)")
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToOne(optional = false,fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id" , referencedColumnName = "id")
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
