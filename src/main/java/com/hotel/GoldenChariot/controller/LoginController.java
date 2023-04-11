package com.hotel.GoldenChariot.controller;

import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.service.admin.AdminService;
import com.hotel.GoldenChariot.service.customer.CustomerService;
import com.hotel.GoldenChariot.utility.Dropdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/loginForm")
    public String loginForm(Model model){
        var dropdownRole = Dropdown.dropdownRole();
        model.addAttribute("listRole", dropdownRole);
        model.addAttribute("dto", new LoginDto());
        return "/login/loginForm";
    }

    @GetMapping("/transit")
    public String transit(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();

        if (adminService.getDataAdmin(username) != null){
            var accountAdmin = adminService.getDataAdmin(username);
            if (accountAdmin.getRole().equals("Admin")){
                return "redirect:/home/admin";
            } else {
                return "redirect:/admin/index";
            }
        } else {
            return "redirect:/home/customer";
        }
    }
}
