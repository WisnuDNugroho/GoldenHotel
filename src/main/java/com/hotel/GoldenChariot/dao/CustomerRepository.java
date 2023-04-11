package com.hotel.GoldenChariot.dao;

import com.hotel.GoldenChariot.dto.customer.CustomerDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.login.LoginDto(cust.username, cust.password, 'Customer')
            FROM Customer AS cust
            WHERE cust.username = :username
            """)
    public LoginDto getDataCustomer(@Param("username") String username);

    @Query("""
            SELECT COUNT(cust.username)
            FROM Customer AS cust
            WHERE cust.fullName LIKE %:fullName%
            """)
    public Long getCount(@Param("fullName") String fullName);

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.customer.CustomerDto(cust.fullName, cust.email, cust.address, cust.username)
            FROM Customer AS cust
            WHERE cust.fullName LIKE %:fullName%
            """)
    public List<CustomerDto> findAll(@Param("fullName") String fullName, Pageable pageable);
}
