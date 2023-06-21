package com.example.studentapp.service;

import com.example.studentapp.enums.Level;
import com.example.studentapp.model.SessionRequest;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.repository.SessionRepository;
import com.example.studentapp.repository.TeacherRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Stack;

import static com.example.studentapp.enums.Level.*;
import static com.example.studentapp.enums.Level.BRONZE;
import static com.example.studentapp.enums.TeacherStatus.*;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    SessionRepository sessionRepository;

    public List<Teacher> searchTeachers(Long subjectId) {
        return teacherRepository.findTeachersBySubjects(subjectId);
    }

    public Teacher create(Teacher teacher){
        teacher.setWallet(0.0F);
        teacher.setStatus(PENDING);
        teacher.setLevel(Level.BRONZE);
        teacher.setFirstLogin(true);
        teacher.setHoursTaught(0.0F);
        teacher.setRating(0.0F);
        return teacherRepository.save(teacher);
    }
    public Teacher login(String email, String password){
        Teacher teacher = teacherRepository.findTeacherByEmail(email);
        if(teacher.getPassword().equals(password)){
            return teacher;
        }
        return null;
    }
    public void changeRate(Teacher teacher, Level level){
        teacher.setLevel(level);
        if(level == BRONZE){
            teacher.setHourlyRate(200.0F);
        } else if(level == SILVER){
            teacher.setHourlyRate(250.0F);
        } else if(level == GOLD){
            teacher.setHourlyRate(300.0F);
        } else if(level == PLATINUM){
            teacher.setHourlyRate(450.0F);
        }
    }

    public List<Teacher> listAll(){
        List<Teacher> teachers = teacherRepository.findTeachersByStatusIs(ACCEPTED);
        teachers.sort((t1, t2) -> t2.getRating().compareTo(t1.getRating()));
        for(Teacher teacher: teachers) {
            if (teacher.getProfilePicture() != null) {
                String base64EncodedImage = Base64.encodeBase64String(teacher.getProfilePicture());
                teacher.setBase64EncodedImage(base64EncodedImage);
            }
        }
        return teachers;
    }

    public Teacher getTeacherById(Long id){
        return teacherRepository.findById(id).get();
    }
    public Teacher updateTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getPendingTeachers(){
        return teacherRepository.findTeachersByStatusIs(PENDING);
    }

    public void reject(Long id){
        Teacher teacher = teacherRepository.findById(id).get();
        teacher.setStatus(REJECTED);
        teacherRepository.save(teacher);
    }

    public void accept(Long id, Level level){
        Teacher teacher = teacherRepository.findById(id).get();
        teacher.setStatus(ACCEPTED);
        changeRate(teacher, level);
        teacherRepository.save(teacher);
    }

    public void modify(Long id, Level level){
        Teacher teacher = teacherRepository.findById(id).get();
        changeRate(teacher, level);
        teacherRepository.save(teacher);
    }

    public Teacher forgotPassword(String email, String password){
        Teacher teacher = teacherRepository.findTeacherByEmail(email);
        if(teacher != null){
            teacher.setPassword(password);
            return teacherRepository.save(teacher);
        }
        return null;
    }
    public void deleteTeacher(Long id) {
        List<SessionRequest> sessionRequests = sessionService.listAllByTeacherId(id);
        for(SessionRequest sessionRequest : sessionRequests){
            sessionRepository.deleteById(sessionRequest.getId());
        }
        teacherRepository.deleteById(id);
    }

    public List<Teacher> searchTeachersBySubjects(String subject){
        return teacherRepository.searchTeachersBySubjectsContainsIgnoreCase(subject);
    }
}
