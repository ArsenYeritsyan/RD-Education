package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import java.util.Objects;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "grade")
public class Grade {
  @Id
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", columnDefinition = "CHAR(36)")
  private UUID id;

  @OneToOne
  @JoinColumn(name = "homework_id")
  private Homework homework;

  @Column(name = "grade")
  private BigDecimal grade;

  @Column(name = "feedback")
  private String feedback;

  public Grade(BigDecimal grade, String feedback) {
    this.grade = grade;
    this.feedback = feedback;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Grade grade1 = (Grade) o;
    return Objects.equals(id, grade1.id)
        && Objects.equals(homework, grade1.homework)
        && Objects.equals(grade, grade1.grade)
        && Objects.equals(feedback, grade1.feedback);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, homework, grade, feedback);
  }
}
