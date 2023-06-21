package com.example.studentapp.service;

import com.example.studentapp.enums.SessionStatus;
import com.example.studentapp.model.Admin;
import com.example.studentapp.model.SessionRequest;
import com.example.studentapp.repository.AdminRepository;
import com.example.studentapp.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.example.studentapp.enums.SessionStatus.*;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    SessionRepository sessionRepository;

    public Admin login(String username, String password){
        Admin admin = adminRepository.findAdminByUsername(username);
        if(admin.getPassword().equals(password)){
            return admin;
        }
        return null;
    }

    public void addToWallet(float amount) {
        Admin admin = adminRepository.findAdminByUsername("admin");
        admin.setWallet(admin.getWallet() + amount);
        adminRepository.save(admin);
    }

    public List<SessionRequest> getRequests() {
        List<SessionRequest> sessionRequests = new LinkedList<>();
        sessionRequests.addAll(sessionRepository.findAllByStatusIs(PENDING));
        sessionRequests.addAll(sessionRepository.findAllByStatusIs(ACCEPTED));
        sessionRequests.addAll(sessionRepository.findAllByStatusIs(PENDINGPAYMENT));
        return sessionRequests;
    }
    public List<SessionRequest> getUpdates() {
        List<SessionRequest> sessionRequests = new LinkedList<>();
        sessionRequests.addAll(sessionRepository.findAllByStatusIs(REJECTED));
        sessionRequests.addAll(sessionRepository.findAllByStatusIs(COMPLETEDSTUDENT));
        sessionRequests.addAll(sessionRepository.findAllByStatusIs(HOSTEDTEACHER));
        return sessionRequests;
    }
}
