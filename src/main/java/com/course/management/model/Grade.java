package com.course.management.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
public class Grade implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

   @ManyToOne(optional = false)
   @JoinColumn(name = "student_id")
   private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
@JoinColumn(name = "course_id")
private Course course;

@ManyToOne
@JoinColumn(name = "professor_id")
private Professor professor;


private BigDecimal grade;

    public Grade(Student student, Course course, Professor professor, BigDecimal grade) {
        this.student = student;
        this.course = course;
        this.professor = professor;
        this.grade = grade;
    }

    public Grade() {

    }

    public Professor getProfessor() {
        return professor;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getGrade() {
        return grade;
    }
}
