package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Transient
    private String confirmPassword;

    private Date dateOfBirth;

    private String mobileNumber;

    private String gender;

    private String region;

    private String city;

    private String educationalSystem;

    private String grade;
}
