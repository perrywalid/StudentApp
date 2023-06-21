package com.example.studentapp.service;

import com.example.studentapp.enums.SessionStatus;
import com.example.studentapp.model.SessionRequest;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.repository.SessionRepository;
import com.example.studentapp.repository.StudentRepository;
import com.example.studentapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.studentapp.enums.SessionStatus.*;

@Service
public class SessionService {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminService adminService;

    public void payForSession(Long sessionId) {
        SessionRequest session = sessionRepository.findById(sessionId).get();
        session.setStatus(ACCEPTED);
        Teacher teacher = session.getTeacher();
        Float appRevenue = session.getCost() * 30 / 100;
        session.setCost(session.getCost() - appRevenue);
        adminService.addToWallet(appRevenue);
        teacherRepository.save(teacher);
        sessionRepository.save(session);
    }

    public SessionRequest requestSession(Long studentId, Long teacherId, SessionRequest sessionRequest){
        sessionRequest.setStatus(PENDING);
        sessionRequest.setTeacher(teacherRepository.findById(teacherId).get());
        sessionRequest.setStudent(studentRepository.findById(studentId).get());
        sessionRequest.setCost(sessionRequest.getTeacher().getHourlyRate() * sessionRequest.getSessionHours());
        return sessionRepository.save(sessionRequest);
    }
    public List<SessionRequest> listAllByStudentId(Long studentId){
        return sessionRepository.findAllByStudentId(studentId);
    }
    public List<SessionRequest> listAllByTeacherId(Long teacherId){
        return sessionRepository.findAllByTeacherId(teacherId);
    }
    public List<SessionRequest> listAllByStudentIdAndStatus(Long studentId, SessionStatus status){
        return sessionRepository.findAllByStudentIdAndStatusIs(studentId, status);
    }
    public List<SessionRequest> listAllByTeacherIdAndStatus(Long teacherId, SessionStatus status){
        return sessionRepository.findAllByTeacherIdAndStatusIs(teacherId, status);
    }
    public SessionRequest host(Long sessionId){
        SessionRequest session = sessionRepository.findById(sessionId).get();
        session.setStatus(HOSTEDTEACHER);
        return sessionRepository.save(session);
    }
    public SessionRequest attend(Long sessionId){
        SessionRequest session = sessionRepository.findById(sessionId).get();
        Teacher teacher = session.getTeacher();
        teacher.setWallet(teacher.getWallet() + session.getCost());
        addToTeacherHoursCompleted(teacher, session);
        teacherRepository.save(teacher);
        session.setStatus(COMPLETEDSTUDENT);
        return sessionRepository.save(session);
    }
    public void accept(Long sessionId){
        SessionRequest session = sessionRepository.findById(sessionId).get();
        session.setStatus(PENDINGPAYMENT);
        sessionRepository.save(session);
    }
    public void reject(Long sessionId){
        SessionRequest session = sessionRepository.findById(sessionId).get();
        session.setStatus(REJECTED);
        sessionRepository.save(session);
    }

    public SessionRequest getSessionById(Long id) {
        return sessionRepository.findById(id).get();
    }

    public void submitReview(Long sessionId, Integer rating) {
        SessionRequest session = sessionRepository.findById(sessionId).get();
        session.setRating(rating);
        sessionRepository.save(session);
        Teacher teacher = session.getTeacher();
        recalculateTeacherRating(teacher);
        teacherRepository.save(teacher);
    }
    public void recalculateTeacherRating(Teacher teacher){
        List<SessionRequest> sessionRequests = sessionRepository.findAllByTeacherIdAndStatusIs(teacher.getId(), COMPLETEDSTUDENT);
        Float totalRating = 0.0F;
        for(SessionRequest sessionRequest : sessionRequests){
            totalRating += sessionRequest.getRating();
        }
        teacher.setRating(totalRating / sessionRequests.size());
    }

    public void addToTeacherHoursCompleted(Teacher teacher, SessionRequest sessionRequest){
        teacher.setHoursTaught(teacher.getHoursTaught() + sessionRequest.getSessionHours());
    }

    public List<SessionRequest> listAllByStatus(SessionStatus status){
        return sessionRepository.findAllByStatusIs(status);
    }
}
