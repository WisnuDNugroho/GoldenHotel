package com.hotel.GoldenChariot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/admin")
    public String admin(Model model){
        return "/home/admin";
    }

    @GetMapping("/superAdmin")
    public String superAdmin(Model model){
        return "/home/superAdmin";
    }

    @GetMapping("/customer")
    public String customer(Model model){
        return "/home/customer";
    }
}
