package com.example.studentapp.repository;

import com.example.studentapp.model.SessionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionRequest, Long> {
}
