package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user")
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "mail", unique = true, nullable = false)
  private String mail;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  @JsonManagedReference
  private Set<Role> roles = new HashSet<>();

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "dob", nullable = false)
  private LocalDate dob;

  @Column(name = "picture_id")
  private String pictureId;

  public User(
          String password,
          String mail,
          String firstName,
          String lastName,
          LocalDate dob,
          String pictureId) {
    this.password = password;
    this.mail = mail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.pictureId = pictureId;
  }
}