package com.example.studentapp.repository;

import com.example.studentapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

    @Query
    public Admin findAdminByUsername(String username);
}
