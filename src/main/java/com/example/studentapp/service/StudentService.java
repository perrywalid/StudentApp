package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student)
    {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student login(Student tryStudent){
        Student student = studentRepository.findStudentByEmail(tryStudent.getEmail());
        if(student.getPassword().equals(tryStudent.getPassword())){
            return student;
        }
        return null;
    }

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

}
