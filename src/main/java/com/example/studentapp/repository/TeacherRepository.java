package com.example.studentapp.repository;

import com.example.studentapp.enums.TeacherStatus;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query
    public List<Teacher> findTeachersBySubjects(Long subjectId);
    @Query
    public Teacher findTeacherByEmail(String email);

    @Query
    public List<Teacher> findTeachersByStatusIs(TeacherStatus status);

    @Query
    public List<Teacher> searchTeachersBySubjectsContainsIgnoreCase(String subject);
}
