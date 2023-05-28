package com.example.studentapp.controller;

import com.example.studentapp.model.Teacher;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/login")
    public List<Teacher> searchTeachers(@RequestParam("subject") Long subjectId) {
        return teacherService.searchTeachers(subjectId);
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody Teacher teacher){
        if(teacherService.create(teacher) != null) {
            return new ResponseEntity<>("Teacher registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error during registration!", HttpStatus.UNAUTHORIZED);
    }
}
