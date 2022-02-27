package com.course.management.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
public class Course implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;



    public Course(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public Course() {

    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty_id() {
        return faculty;
    }

    public UUID getId() {
        return id;
    }
}
