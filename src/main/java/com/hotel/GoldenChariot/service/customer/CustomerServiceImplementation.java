package com.hotel.GoldenChariot.service.customer;

import com.hotel.GoldenChariot.dao.CustomerRepository;
import com.hotel.GoldenChariot.dao.ReservationRepository;
import com.hotel.GoldenChariot.dto.customer.CustomerDto;
import com.hotel.GoldenChariot.dto.customer.CustomerRegisterDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.dto.reservation.DetailReservationDto;
import com.hotel.GoldenChariot.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginDto getDataCustomer(String username) {
        return customerRepository.getDataCustomer(username);
    }

    @Override
    public List<CustomerDto> getListCustomer(Integer page, String fullName) {
        Integer getRowsPage = 3;
        Pageable pagination = PageRequest.of(page-1, getRowsPage);
        return customerRepository.findAll(fullName, pagination);
    }

    @Override
    public Long getCountPage(String fullName) {
        var rowPerPage = 3;
        double totalData = (double) customerRepository.getCount(fullName);
        long totalPage = (long) (Math.ceil(totalData / rowPerPage));
        return totalPage;
    }

    @Override
    public List<DetailReservationDto> getListTransaction(Integer page, String username) {
        Integer getRowsPage = 3;
        Pageable pagination = PageRequest.of(page-1, getRowsPage);
        return reservationRepository.findTransaction(username, pagination);
    }

    @Override
    public Long getCountTransaction(String username) {
        var rowPerPage = 3;
        double totalData = (double) reservationRepository.getCountTransaction(username);
        long totalPage = (long) (Math.ceil(totalData / rowPerPage));
        return totalPage;
    }

    @Override
    public String getFullName(String username) {
        return customerRepository.getReferenceById(username).getFullName();
    }

    @Override
    public boolean checkDependency(String username) {
        if (reservationRepository.checkDependencyCustomer(username) > 0){
            return false;
        } else {
            customerRepository.deleteById(username);
            return true;
        }
    }

    @Override
    public void register(CustomerRegisterDto dto) {
        String encoderPassword = passwordEncoder.encode(dto.getPassword());
        Customer customer = new Customer(dto.getUsername(), encoderPassword, dto.getFullname(),
                                        dto.getEmail(), dto.getAddress());
        customerRepository.save(customer);
    }
}
