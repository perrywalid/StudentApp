package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Admin {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String username;

    public String password;

    public float wallet;
}
