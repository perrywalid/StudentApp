package com.example.studentapp.model;

//Math English Arabic Science Physis Bio Chemistry History Geography Religion Art Music PE UNI: Accounting, finance

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class Subject {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers = new HashSet<>();


}
