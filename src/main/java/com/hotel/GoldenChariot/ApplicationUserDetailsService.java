package com.hotel.GoldenChariot;

import com.hotel.GoldenChariot.dao.AdminRepository;
import com.hotel.GoldenChariot.dao.CustomerRepository;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LoginDto userLogin = new LoginDto();
        var role = request.getParameter("role");
        if (adminRepository.getDataAdmin(username) != null){
            var account = adminRepository.getDataAdmin(username);
            if (account.getRole().equals(role)){
                userLogin = new LoginDto(account.getUsername(), account.getPassword(), account.getRole());
            } else {
                userLogin = new LoginDto();
            }
        } else if (customerRepository.getDataCustomer(username) != null){
            var accountCust = customerRepository.getDataCustomer(username);
            if (accountCust.getRole().equals(role)){
                userLogin = new LoginDto(accountCust.getUsername(), accountCust.getPassword(), accountCust.getRole());
            } else {
                userLogin = new LoginDto();
            }
        } else {
            userLogin = new LoginDto();
        }
        return new ApplicationUserDetails(userLogin);
    }
}
