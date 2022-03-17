package com.javagang.rdcoursemanagementplatform.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role_type", nullable = false, columnDefinition = "CHAR(14)")
  private RoleType roleType;

  public Role(RoleType roleType) {
    this.roleType = roleType;
  }
}
