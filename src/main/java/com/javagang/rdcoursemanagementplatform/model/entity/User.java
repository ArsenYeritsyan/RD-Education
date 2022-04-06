package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Set;
import java.util.UUID;
import java.util.HashSet;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user")
public class User {
  @Id
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", columnDefinition = "CHAR(36)")
  private UUID id;

  @Column(name = "mail", unique = true, nullable = false)
  private String mail;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
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

  @Override
  public String toString() {
    return String.format(
            "User{id=%s, mail='%s', roles=%s, firstName='%s', lastName='%s', dob=%s, pictureId='%s'}",
            id, mail, roles, firstName, lastName, dob, pictureId);
  }
}