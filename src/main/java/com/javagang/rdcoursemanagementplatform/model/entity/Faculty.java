package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String faculty_name;

    @OneToMany(mappedBy = "faculty")
    private Set<Professor> professors = new HashSet<>();

    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses = new HashSet<>();

    public Faculty(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public Faculty() {

    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", faculty_name='" + faculty_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && faculty_name.equals(faculty.faculty_name) && Objects.equals(professors, faculty.professors) && Objects.equals(courses, faculty.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty_name, professors, courses);
    }
}
