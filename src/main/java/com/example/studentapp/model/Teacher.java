package com.example.studentapp.model;

import com.example.studentapp.enums.Level;
import com.example.studentapp.enums.TeacherStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table
public class Teacher {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private Date dateOfBirth;

    private String mobileNumber;

    private String gender;

    private String region;

    private String city;

    private String experience;

    @Transient
    private List<Long> subjectIds;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new LinkedList<>();
    private Float wallet;
    private TeacherStatus status;
    private Float hourlyRate;
    private Level rate;
}
