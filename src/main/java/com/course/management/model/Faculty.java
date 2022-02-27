package com.course.management.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Faculty implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String faculty_name;

    @OneToMany(mappedBy = "faculty")
    private Set<Professor> professors=new HashSet<>();

    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    public Faculty(String faculty_name) {
        this.faculty_name = faculty_name;
    }

//private Set<Course> courses = new HashSet<>();

    public Faculty() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return faculty_name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
