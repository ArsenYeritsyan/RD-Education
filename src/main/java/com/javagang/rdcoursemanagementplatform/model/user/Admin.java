package com.javagang.rdcoursemanagementplatform.model.user;

import java.time.LocalDate;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "admin")
public class Admin extends User {

  public Admin(
      String password,
      String mail,
      String firstName,
      String lastName,
      LocalDate dob,
      String pictureId) {
    super(password, mail, firstName, lastName, dob, pictureId);
  }
}
