package com.example.studentapp.model;

import com.example.studentapp.enums.SessionStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
public class SessionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime requestedDateTime;

    private String subject;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    private String notes;

    private String phoneNumber;

    private float sessionHours;

    @Transient
    private String test;

    private float cost;

    @Column(nullable = true)
    private Integer rating;
}
