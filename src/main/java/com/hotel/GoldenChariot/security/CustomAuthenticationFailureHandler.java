package com.hotel.GoldenChariot.security;

import com.hotel.GoldenChariot.dao.AdminRepository;
import com.hotel.GoldenChariot.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.unbescape.xml.XmlEscape;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        var link = "/login/loginForm";
        String message = "";
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var role = request.getParameter("role");

        if (username == null || username.equals("") || password == null || password.equals("") || role == null || role.equals("")){
            message = "Username or Password or Role is Required";
        } else {
            var adminAccount = adminRepository.getDataAdmin((String) username);
            var customerAccount = customerRepository.getDataCustomer((String) username);
            if ((adminAccount != null && (!adminAccount.getRole().equals(role))) || (customerAccount != null && (!customerAccount.getRole().equals(role)))){
                message = "Role is Not Match With Your Account";
            } else {
                message = "Your Username or Password is Incorrect";
            }
        }
        response.sendRedirect(request.getContextPath() + link + "?errors=" + message);
    }
}
