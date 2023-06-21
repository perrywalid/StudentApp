package com.example.studentapp.controller;

import com.example.studentapp.enums.SessionStatus;
import com.example.studentapp.enums.TeacherStatus;
import com.example.studentapp.model.Teacher;
import com.example.studentapp.service.SessionService;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.IOException;

import static com.example.studentapp.enums.SessionStatus.ACCEPTED;
import static com.example.studentapp.enums.SessionStatus.PENDING;
import static com.example.studentapp.enums.TeacherStatus.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    @Autowired
    SessionService sessionService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Teacher teacher = teacherService.login(email, password);
        ModelAndView mav = new ModelAndView();
        if (teacher != null) {
            if (teacher.getStatus() == TeacherStatus.ACCEPTED) {
                Long id = teacher.getId();
                if (teacher.isFirstLogin()) {
                    teacher.setFirstLogin(false);
                    teacherService.updateTeacher(teacher);
                    mav.setViewName("redirect:/teachers/tutor-accepted/" + id);
                } else {
                    mav.setViewName("redirect:/teachers/upcoming-sessions/" + id);
                }
            } else if (teacher.getStatus() == REJECTED) {
                mav.setViewName("redirect:/teachers/tutor-rejected");
            } else if (teacher.getStatus() == TeacherStatus.PENDING) {
                mav.setViewName("redirect:/teachers/tutor-pending");
            }
        }
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("tutor-login");
    }
    @GetMapping("/tutor-pending")
    public ModelAndView pending() {
        return new ModelAndView("tutor-pending");
    }
    @GetMapping("/tutor-rejected")
    public ModelAndView rejected() {
        return new ModelAndView("tutor-rejected");
    }
    @GetMapping("/tutor-accepted/{id}")
    public ModelAndView accepted(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        ModelAndView mav = new ModelAndView("tutor-accepted");
        mav.addObject("teacher", teacher);
        return mav;
    }
    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/upcoming-sessions/{id}")
    public ModelAndView upcomingSessions(@PathVariable Long id)
    {
        ModelAndView mav = new ModelAndView("tutor-up-sess");
        mav.addObject("teacher", teacherService.getTeacherById(id));
        mav.addObject("sessionRequests", sessionService.listAllByTeacherIdAndStatus(id, ACCEPTED));
        return mav;
    }
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("teacher") Teacher teacher) throws IOException {
        MultipartFile profilePicture = teacher.getProfilePictureMulti().getFile();
        if (profilePicture != null && !profilePicture.isEmpty()) {
            teacher.setProfilePicture(profilePicture.getBytes());
        }
        if (teacherService.create(teacher) != null) {
            return new ModelAndView("tutor-pending");
        }
        return null;
    }
    @GetMapping("/register")
    public ModelAndView displayRegister(Teacher teacher){
        return new ModelAndView("tutor-register");
    }

    @GetMapping("/{id}")
    public ModelAndView getTeacher(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("teacher-profile");
        mav.addObject("teacher", teacherService.getTeacherById(id));
        return mav;
    }
    @GetMapping("/host-session/{id}/{sessionId}")
    public ModelAndView hostSession(@PathVariable Long id, @PathVariable Long sessionId) {
        sessionService.host(sessionId);
        return new ModelAndView("redirect:/teachers/upcoming-sessions/" + id);
    }

    @GetMapping("/request-list/{id}")
    public ModelAndView requestList(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("tutor-req-list");
        mav.addObject("teacher", teacherService.getTeacherById(id));
        mav.addObject("sessionRequests", sessionService.listAllByTeacherIdAndStatus(id, PENDING));
        return mav;
    }
    @GetMapping("/accept-request/{id}/{sessionId}")
    public ModelAndView acceptRequest(@PathVariable Long id, @PathVariable Long sessionId) {
        sessionService.accept(sessionId);
        return new ModelAndView("redirect:/teachers/request-list/" + id);
    }
    @GetMapping("/reject-request/{id}/{sessionId}")
    public ModelAndView rejectRequest(@PathVariable Long id, @PathVariable Long sessionId) {
        sessionService.reject(sessionId);
        return new ModelAndView("redirect:/teachers/request-list/" + id);
    }
    @GetMapping("/forgot")
    public ModelAndView displayForgotPassword() {
        return new ModelAndView("teacher-forget");
    }

    @PostMapping("/forgot")
    public ModelAndView forgotPassword( @RequestParam("email") String email, @RequestParam("password") String password) {
        teacherService.forgotPassword(email, password);
        return new ModelAndView("redirect:/teachers/login");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return new ModelAndView("redirect:/teachers/login");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:/home");
    }

}
