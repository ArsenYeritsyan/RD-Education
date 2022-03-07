package com.javagang.rdcoursemanagementplatform.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue
    private UUID id;

    private Double grade;
/*
    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Professor professor;
*/
}
