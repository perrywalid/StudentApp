package com.example.studentapp.controller;

import com.example.studentapp.model.SessionRequest;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.service.SessionService;
import com.example.studentapp.service.StudentService;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import static com.example.studentapp.enums.SessionStatus.ACCEPTED;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/register")
    public ModelAndView displayRegisterStudent(Student student) {
        return new ModelAndView("student-register");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Student student = studentService.login(email, password);
        ModelAndView mav = new ModelAndView();
        if (student != null) {
            mav.setViewName("redirect:/students/home?studentId=" + student.getId());
        } else {
            mav.setViewName("redirect:/students/login"); //TODO: Create a login failed alert
        }
        return mav;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("student-login");
    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam("studentId") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        ModelAndView mav = new ModelAndView("student-home");
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/tutor-selection/{id}")
    public ModelAndView listAll(@PathVariable Long id, @RequestParam(defaultValue = "") String subject) {
        Student student = studentService.getStudentById(id);
        ModelAndView mav = new ModelAndView("student-tut-selection");
        mav.addObject("student", student);
        if(subject.length() > 0){
            mav.addObject("teachers", teacherService.searchTeachersBySubjects(subject));
        } else {
            mav.addObject("teachers", teacherService.listAll());
        }
        return mav;
    }

    @GetMapping("/session-request/{id}/{teacherId}")
    public ModelAndView displayRequest(@PathVariable Long id, @PathVariable Long teacherId, SessionRequest sessionRequest) {
        Student student = studentService.getStudentById(id);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        ModelAndView mav = new ModelAndView("student-make-request");
        mav.addObject("student", student);
        mav.addObject("teacher", teacher);
        return mav;
    }

    @PostMapping("/session-request/{id}/{teacherId}")
    public ModelAndView makeRequest(@PathVariable Long id, @PathVariable Long teacherId, SessionRequest sessionRequest) {
        sessionService.requestSession(id, teacherId, sessionRequest);
        return new ModelAndView("redirect:/students/session-request-list/" + id);
    }

    @GetMapping("/session-request-list/{id}")
    public ModelAndView displayRequestList(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("student-req-list");
        mav.addObject("student", studentService.getStudentById(id));
        mav.addObject("sessionRequests", sessionService.listAllByStudentId(id));
        return mav;
    }

    @GetMapping("/pay/{id}/{sessionId}")
    public ModelAndView displayPayment(@PathVariable Long id, @PathVariable Long sessionId) {
        ModelAndView mav = new ModelAndView("student-payment");
        mav.addObject("student", studentService.getStudentById(id));
        mav.addObject("sessionRequest", sessionService.getSessionById(sessionId));
        return mav;
    }

    @PostMapping("/pay/{id}/{sessionId}")
    public ModelAndView makePayment(@PathVariable Long id, @PathVariable Long sessionId) {
        sessionService.payForSession(sessionId);
        return new ModelAndView("redirect:/students/session-request-list/" + id);
    }

    @GetMapping("/upcoming-sessions/{id}")
    public ModelAndView upcomingSessions(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("student-up-sess");
        mav.addObject("student", studentService.getStudentById(id));
        mav.addObject("sessionRequests", sessionService.listAllByStudentIdAndStatus(id, ACCEPTED));
        return mav;
    }

    @GetMapping("/attend-session/{id}/{sessionId}")
    public ModelAndView attendSession(@PathVariable Long id, @PathVariable Long sessionId) {
        sessionService.attend(sessionId);
        return new ModelAndView("redirect:/students/review/" + id + "/" + sessionId);
    }

    @GetMapping("/review/{id}/{sessionId}")
    public ModelAndView review(@PathVariable Long id, @PathVariable Long sessionId) {
        ModelAndView mav = new ModelAndView("student-review");
        mav.addObject("student", studentService.getStudentById(id));
        mav.addObject("sessionRequest", sessionService.getSessionById(sessionId));
        return mav;
    }

    @PostMapping("/submit-review/{id}/{sessionId}")
    public ModelAndView submitReview(@PathVariable Long id, @PathVariable Long sessionId, @RequestParam("rating") int rating) {
        sessionService.submitReview(sessionId, rating);
        return new ModelAndView("redirect:/students/upcoming-sessions/" + id);
    }

    @PostMapping("/register")
    public ModelAndView registerStudent(Student student) {
        studentService.create(student);
        return new ModelAndView("redirect:/students/login");
    }

    @GetMapping("/forgot")
    public ModelAndView displayForgotPassword() {
        return new ModelAndView("student-forget");
    }

    @PostMapping("/forgot")
    public ModelAndView forgotPassword( @RequestParam("email") String email, @RequestParam("password") String password) {
        studentService.forgotPassword(email, password);
        return new ModelAndView("redirect:/students/login");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ModelAndView("redirect:/students/login");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:/home");
    }
}
