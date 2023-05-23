package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class SessionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

}
