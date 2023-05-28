package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class AppWallet {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    float teacherWallet;

    float balance;
}
