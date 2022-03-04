package com.javagang.rdcoursemanagementplatform.model.user;

import com.javagang.rdcoursemanagementplatform.model.role.Role;

import lombok.Getter;
import lombok.Setter;

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
public class User {
  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private UUID id;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @Column(name = "mail", unique = true, nullable = false)
  private String mail;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "dob", nullable = false)
  private LocalDate dob;

  @Column(name = "picture_id")
  private String pictureId;

  public User() {}

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
