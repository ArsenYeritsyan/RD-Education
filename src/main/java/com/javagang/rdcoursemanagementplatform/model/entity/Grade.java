package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    private BigDecimal grade;

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
        return Objects.equals(id, grade1.id) && Objects.equals(homework, grade1.homework) && Objects.equals(grade, grade1.grade) && Objects.equals(feedback, grade1.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homework, grade, feedback);
    }
}
