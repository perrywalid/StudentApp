package com.example.studentapp.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Transaction {
    private Long id;
    private Long studentId;
    private Long teacherId;
    private Float total;
}
