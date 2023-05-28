package com.example.studentapp.repository;

import com.example.studentapp.model.AppWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppWalletRepository extends JpaRepository<AppWallet, Long>{

}
