package com.hotel.GoldenChariot.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private String fullName;
    private String email;
    private String address;
    private String username;
}
