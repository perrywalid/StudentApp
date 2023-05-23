package com.example.studentapp.service;

import com.example.studentapp.model.Subject;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.repository.TeacherRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepositary teacherRepositary;

    public List<Teacher> searchTeachers(Long subjectId) {
        return teacherRepositary.findTeachersBySubjectId(subjectId);
    }
}
