package com.example.studentapp.controller;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> searchTeachers(@RequestParam("subject") String subject,
                                        @RequestParam("availability") LocalDateTime availability) {
        return teacherService.searchTeachers(subject, availability);
    }
}
