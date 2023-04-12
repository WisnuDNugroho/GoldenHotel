package com.hotel.GoldenChariot.service.customer;

import com.hotel.GoldenChariot.dto.customer.CustomerDto;
import com.hotel.GoldenChariot.dto.customer.CustomerRegisterDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.dto.reservation.DetailReservationDto;

import java.util.List;

public interface CustomerService {
    public LoginDto getDataCustomer(String username);
    public List<CustomerDto> getListCustomer(Integer page, String fullName);
    public Long getCountPage(String fullName);
    public List<DetailReservationDto> getListTransaction(Integer page, String username);
    public Long getCountTransaction(String username);
    public String getFullName(String username);
    public boolean checkDependency(String username);

    public void register(CustomerRegisterDto dto);
}
