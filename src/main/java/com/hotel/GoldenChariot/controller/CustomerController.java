package com.hotel.GoldenChariot.controller;

import com.hotel.GoldenChariot.dto.customer.CustomerRegisterDto;
import com.hotel.GoldenChariot.service.admin.AdminService;
import com.hotel.GoldenChariot.service.customer.CustomerService;

import ch.qos.logback.core.joran.conditional.ElseAction;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String fullName
                        ){
        model.addAttribute("listDto", customerService.getListCustomer(page, fullName));
        long totalPage = customerService.getCountPage(fullName);
        if (totalPage == 0) {
            page = 1;
            totalPage = 1;
        }

        model.addAttribute("fullName", fullName);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);

        return "/customer/index";
    }

    @GetMapping("/transactionHistory")
    public String transactionHistory(Model model,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(required = true) String username
    ){
        model.addAttribute("listDto", customerService.getListTransaction(page, username));
        model.addAttribute("fullName", customerService.getFullName(username));
        long totalPage = customerService.getCountTransaction(username);
        if (totalPage == 0) {
            page = 1;
            totalPage = 1;
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);

        return "/customer/transactionHistory";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(required = true) String username
    ){
        boolean bool = customerService.checkDependency(username);
        if (bool){
            return "redirect:/customer/index";
        } else {
            model.addAttribute("fullName", customerService.getFullName(username));
            return "/customer/failDelete";
        }
    }

    @GetMapping("/regsiter")
    public String regsiter(Model model){
        model.addAttribute("dto", new CustomerRegisterDto());
        return "customer/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dot") CustomerRegisterDto dto,
                            BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("dto", dto);
            return "customer/register";
        }
        else {
            customerService.register(dto);
            return "";
        }
    }
}
