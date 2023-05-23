package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).get();
    }

}
