package com.course.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity(name = "student")
@Table
public class Student implements Serializable {

        @Id
        @Column(nullable = false)
        private UUID id;

        @Column(nullable = false)
        @Size(min = 5, message = "Name must be at least 5 characters long")
        private String first_name;

        @Column(nullable = false)
//    @Size(min = 2, message = "Name must be at least 5 characters long")
        private String last_name;

        @Column(nullable = false)
        @JsonIgnore
        private String password;

        @Email
        @NotEmpty
        @Column(unique = true)
        private String email;

        @Column(name = "start_date", nullable = false)
        private LocalDate startDate;

        @Column(name = "groop")
        private String group;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
        private Faculty faculty;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "Student_Courses",
//            joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns = {@JoinColumn(name = "course_id")})
//    private Set<Course> courses = new HashSet<>();

        @OneToMany(mappedBy = "student")
        private Set<Grade> grades;

        public void setGrades(Set<Grade> grades) {
            this.grades = grades;
        }

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

        public UUID getId() {
            return id;
        }

        public Set<Grade> getGrades() {
            return grades;
        }

    }
