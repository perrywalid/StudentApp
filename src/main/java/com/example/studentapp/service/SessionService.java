package com.example.studentapp.service;

import com.example.studentapp.enums.SessionStatus;
import com.example.studentapp.model.AppWallet;
import com.example.studentapp.model.SessionRequest;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.repository.AppWalletRepository;
import com.example.studentapp.repository.SessionRepository;
import com.example.studentapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    AppWalletRepository appWalletRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public void paySession(Long sessionId) {
        SessionRequest session = sessionRepository.findById(sessionId).get();
        session.setStatus(SessionStatus.ACCEPTED);

        Teacher teacher = session.getTeacher();
        Float appRevenue = Math.min(teacher.getHourlyRate() * 30 / 100, 90);
        teacher.setWallet(teacher.getWallet() + teacher.getHourlyRate() - appRevenue);

        AppWallet appWallet = appWalletRepository.findAll().get(0);
        appWallet.setBalance(appWallet.getBalance() + appRevenue);
        appWallet.setTeacherWallet(appWallet.getTeacherWallet() + teacher.getHourlyRate() - appRevenue);

        teacherRepository.save(teacher);
        appWalletRepository.save(appWallet);
        sessionRepository.save(session);
    }

    // public void rejectSession(Long sessionId) {
    // requestRequestSession
    public void requestSession(){
        // SessionRequest session = sessionRepository.findById(sessionId).get();
        // session.setStatus(SessionStatus.REJECTED);
        // sessionRepository.save(session);
    }
}
