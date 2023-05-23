package com.example.studentapp.repository;

import com.example.studentapp.model.Subject;
import com.example.studentapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepositary extends JpaRepository<Teacher, Long> {
    @Query
    public List<Teacher> findTeachersBySubjectId(Long subjectId);
}
