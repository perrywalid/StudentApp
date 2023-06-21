package com.example.studentapp.controller;

import com.example.studentapp.enums.Level;
import com.example.studentapp.service.AdminService;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("/login")
    public ModelAndView displayLogin(){
        return new ModelAndView("admin");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password){
        ModelAndView mav = new ModelAndView();
        if(adminService.login(username, password) != null){
            mav.setViewName("redirect:/admin/dashboard");
        }else{
            mav.setViewName("redirect:/admin/login");
        }
        return mav;
    }
    @GetMapping("/dashboard")
    public ModelAndView dashboard(){
        ModelAndView mav = new ModelAndView("admin-selection");
        mav.addObject("teachers",teacherService.getPendingTeachers());
        return mav;
    }

    @GetMapping("/reject/{id}")
    public ModelAndView reject(@PathVariable Long id){
        teacherService.reject(id);
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping("/accept/{id}")
    public ModelAndView accept(@PathVariable Long id, @RequestParam("level") String level){
        teacherService.accept(id, Level.valueOf(level.toUpperCase()));
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping("/sessions")
    public ModelAndView sessions(){
        ModelAndView mav = new ModelAndView("admin-check-sess");
        mav.addObject("requests", adminService.getRequests());
        mav.addObject("updates", adminService.getUpdates());
        return mav;
    }

    @GetMapping("/modify")
    public ModelAndView displayModify (){
        ModelAndView mav = new ModelAndView("admin-modify");
        mav.addObject("teachers", teacherService.listAll());
        return mav;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        teacherService.deleteTeacher(id);
        return new ModelAndView("redirect:/admin/modify");
    }
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable Long id, @RequestParam("level") String level){
        teacherService.modify(id, Level.valueOf(level.toUpperCase()));
        return new ModelAndView("redirect:/admin/modify");
    }
}
