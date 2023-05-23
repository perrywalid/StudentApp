package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class Teacher {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private Date dateOfBirth;

    private String mobileNumber;

    private String gender;

    private String region;

    private String city;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();
    private Float wallet;
}
