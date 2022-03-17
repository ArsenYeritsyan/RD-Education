package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;
import com.javagang.rdcoursemanagementplatform.model.enums.HomeworkState;

import java.util.UUID;
import java.util.Objects;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "homework")
public class Homework {
  @Id
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", columnDefinition = "CHAR(36)")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  @Enumerated(EnumType.STRING)
  @Column(name = "state")
  private HomeworkState state;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne
  @JoinColumn(name = "grade_id")
  private Grade grade;

  public Homework(Student student) {
    this.student = student;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Homework homework = (Homework) o;
    return Objects.equals(id, homework.id)
        && Objects.equals(course, homework.course)
        && state == homework.state
        && Objects.equals(student, homework.student)
        && Objects.equals(grade, homework.grade);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, course, state, student, grade);
  }
}
