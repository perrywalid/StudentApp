package com.example.studentapp.service;

import com.example.studentapp.enums.Level;
import com.example.studentapp.model.Subject;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.repository.SubjectRepository;
import com.example.studentapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.example.studentapp.enums.Level.*;
import static com.example.studentapp.enums.Level.BRONZE;
import static com.example.studentapp.enums.TeacherStatus.*;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Teacher> searchTeachers(Long subjectId) {
        return teacherRepository.findTeachersBySubjects(subjectId);
    }

    public Teacher create(Teacher teacher){
        List<Subject> managedSubjects = new LinkedList<>();
        for(Subject subject: teacher.getSubjects()){
            managedSubjects.add(subjectRepository.findById(subject.getId()).get());
        }
        teacher.setSubjects(managedSubjects);
        teacher.setStatus(PENDING);
        return teacherRepository.save(teacher);
    }
    public Teacher login(String email, String password){
        Teacher teacher = teacherRepository.findTeacherByEmail(email);
        if(teacher.getPassword().equals(password)){
            if(teacher.getStatus() == ACCEPTED) {
                //REDIRECT lel profile 3alatol
                //SIGN IN
                return teacher;
            } else if(teacher.getStatus() == REJECTED){
                //Request rejected
            } else {
                //Pending request
            }
        }
        return null;
    }

    public void accept(Long teacherId, Level level){
        Teacher teacher = teacherRepository.findById(teacherId).get();
        teacher.setStatus(ACCEPTED);
        changeRate(teacherId, level);
        teacherRepository.save(teacher);
    }

    public void changeRate(Long teacherId, Level level){
        Teacher teacher = teacherRepository.findById(teacherId).get();
        teacher.setRate(level);
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
}
