package com.hotel.GoldenChariot.controller;

import com.hotel.GoldenChariot.dto.admin.AdminInsertDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.service.admin.AdminService;
import com.hotel.GoldenChariot.utility.Dropdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page){
        model.addAttribute("listDto", adminService.getListAdmin(page));
        long totalPage = adminService.getCountPage();
        if (totalPage == 0) {
            page = 1;
            totalPage = 1;  
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);

        return "/admin/index";
    }

    @GetMapping("/insert")
    public String insert(Model model){
        AdminInsertDto dto = new AdminInsertDto();
        model.addAttribute("dto", dto);
        return "admin/insert";
    }

    @PostMapping("insert")
    public String insert(@Valid @ModelAttribute("dto") AdminInsertDto dto,
                         BindingResult bindingResult, Model model
    ){
        if (bindingResult.hasErrors()) {
            model.addAttribute("dto", dto);
            return "admin/insert";
        } else {
            adminService.save(dto);
            return "redirect:/admin/index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String username, Model mmodel){
//        Cek ada atau tidak keterikatan data dengant tabel lain
        adminService.delete(username);
        return "redirect:/admin/index";
    }
}
