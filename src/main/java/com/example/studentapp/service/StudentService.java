package com.example.studentapp.service;

import com.example.studentapp.model.SessionRequest;
import com.example.studentapp.model.Student;
import com.example.studentapp.repository.SessionRepository;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    public Student create(Student student)
    {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student login(String email, String password){
        Student student = studentRepository.findStudentByEmail(email);
        if(student == null){
            return null;
        }
        if(student.getPassword().equals(password)){
            return student;
        }
        return null;
    }

    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student forgotPassword(String email, String password){
        Student student = studentRepository.findStudentByEmail(email);
        if(student != null){
            student.setPassword(password);
            return studentRepository.save(student);
        }
        return null;
    }
    public void deleteStudent(Long id) {
        List<SessionRequest> sessionRequests = sessionService.listAllByStudentId(id);
        for(SessionRequest sessionRequest : sessionRequests){
            sessionRepository.deleteById(sessionRequest.getId());
        }
        studentRepository.deleteById(id);
    }
}
