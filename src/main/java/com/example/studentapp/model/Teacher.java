package com.example.studentapp.model;

import com.example.studentapp.enums.Level;
import com.example.studentapp.enums.TeacherStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

    private String confirmPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String mobileNumber;

    private String gender;

    private String region;

    private String city;

    private String nationality;

    private String experience;

    private Float wallet;

    @Enumerated(EnumType.STRING)
    private TeacherStatus status;

    private Float hourlyRate;

    @Enumerated(EnumType.STRING)
    private Level level;

    private float hoursTaught;

    @Column(columnDefinition = "bit default 1")
    private boolean firstLogin;

    private String educationSystem;

    private String schoolGrade;

    private Float rating;

    private String nationalId;

    private String subjects;

    @Transient
    private MultipartFileWrapper profilePictureMulti;

    @Transient
    private String base64EncodedImage;

    @Lob
    private byte[] profilePicture;

}
