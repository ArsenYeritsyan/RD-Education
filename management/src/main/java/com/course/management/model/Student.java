package com.course.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String first_name;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min = 2, message = "Name must be at least 5 characters long")
    private String last_name;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Email
    @NotEmpty
    @Column(name = "Email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "group", nullable = false)
    private String group;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Student_Courses",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses = new HashSet<>();

    public Student(String first_name, String last_name, String password, String email, Faculty faculty, LocalDate startDate, String group) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.faculty = faculty;
        this.startDate = startDate;
        this.group = group;
    }

    public Student() {

    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getGroup() {
        return group;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public UUID getId() {
        return id;
    }
}
