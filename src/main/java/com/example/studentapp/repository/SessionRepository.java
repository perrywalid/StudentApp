package com.example.studentapp.repository;

import com.example.studentapp.enums.SessionStatus;
import com.example.studentapp.model.SessionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionRequest, Long> {
    @Query
    List<SessionRequest> findAllByStudentId(Long id);

    @Query
    List<SessionRequest> findAllByTeacherId(Long id);

    @Query
    List<SessionRequest> findAllByStudentIdAndStatusIs(Long studentId, SessionStatus status);

    @Query
    List<SessionRequest> findAllByTeacherIdAndStatusIs(Long teacherId, SessionStatus status);

    @Query
    List<SessionRequest> findAllByStatusIs(SessionStatus status);
}
