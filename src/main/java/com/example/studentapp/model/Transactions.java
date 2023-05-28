package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Transactions {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // private Long studentId;
    //private Long teacherId;
    //private Float total;
}
