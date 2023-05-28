package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        if(studentService.create(student) != null) {
            return new ResponseEntity<>("Student registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error during registration!", HttpStatus.UNAUTHORIZED);
    }
    //need java function

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student) {
        if(studentService.login(student) != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login failed. Invalid email or password.", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/all")
    public String viewStudents(Model model) {
        List<Student> listStudents = studentService.listAll();
        model.addAttribute("studentsList", listStudents);
        return "students";
    }
}
