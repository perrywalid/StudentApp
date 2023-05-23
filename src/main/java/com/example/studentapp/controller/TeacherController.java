package com.example.studentapp.controller;

import com.example.studentapp.model.Subject;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> searchTeachers(@RequestParam("subject") Long subjectId) {
        return teacherService.searchTeachers(subjectId);
    }
}
