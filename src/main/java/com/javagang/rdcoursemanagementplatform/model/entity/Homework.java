package com.javagang.rdcoursemanagementplatform.model.entity;

import com.javagang.rdcoursemanagementplatform.model.enums.HomeworkState;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Enumerated(EnumType.STRING)
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

    public Homework() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.id) && Objects.equals(course, homework.course) && state == homework.state && Objects.equals(student, homework.student) && Objects.equals(grade, homework.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, state, student, grade);
    }

}
