package com.course.management.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name= "faculties")
public class Faculty implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    @OneToMany
    private Set<Professor> professors=new HashSet<>();

    public Faculty(String name) {
        this.name = name;
    }

//private Set<Course> courses = new HashSet<>();

    public Faculty() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
