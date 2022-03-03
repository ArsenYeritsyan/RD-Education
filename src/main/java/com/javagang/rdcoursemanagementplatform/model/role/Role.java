package com.javagang.rdcoursemanagementplatform.model.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  public Role() {}

  public Role(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
