package com.course.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "professor")
@Table
public class Professor implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min=2, message="Name must be at least 2 characters long")
    private String first_name;

    @Column(nullable = false, unique = true)
    @NotNull
    private String last_name;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Email
    @NotEmpty
    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String picture_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;

//    private Set<String> teaching_group = new HashSet<>();
//
//    private  Set<String> teaching_courses;


    public Professor(String first_name, String last_name, String password, String email, String picture_id, Faculty faculty_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.picture_id = picture_id;
        this.faculty = faculty_id;
    }

    public Professor() {
    }

    public UUID getId() {
        return id;
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

    public String getPicture_id() {
        return picture_id;
    }

    public Faculty getFaculty_id() {
        return faculty;
    }
}
