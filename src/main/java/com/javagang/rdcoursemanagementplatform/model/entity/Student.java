package com.javagang.rdcoursemanagementplatform.model.entity;

import com.javagang.rdcoursemanagementplatform.constant.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends User {

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "grad_date", nullable = false)
    private LocalDate gradDate;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "students_courses",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Homework> homeworks;

    public Student() {
    }

    public Student(
            String password,
            String mail,
            String firstName,
            String lastName,
            LocalDate dob,
            String pictureId) {
        super(password, mail, firstName, lastName, dob, pictureId);
    }

    public Student(
            String password,
            String mail,
            String firstName,
            String lastName,
            LocalDate dob,
            String pictureId,
            LocalDate startDate,
            LocalDate gradDate) {
        super(password, mail, firstName, lastName, dob, pictureId);
        this.startDate = startDate;
        this.gradDate = gradDate;
    }

    public void addHomework(Homework work) {
        if (homeworks.size() < Constants.HOMEWORK_MAX) {
            this.homeworks.add(work);
        } else log.info("assignments is full. Max: " + Constants.HOMEWORK_MAX);
    }

    public void removeHomework(Homework work) {
        this.homeworks.remove(work);
    }

}
