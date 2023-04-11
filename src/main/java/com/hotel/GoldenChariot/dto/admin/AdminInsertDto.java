package com.hotel.GoldenChariot.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminInsertDto {
    private String username;
    private String jobTitle;
    private String password;
    private String passwordConfirm;
}
