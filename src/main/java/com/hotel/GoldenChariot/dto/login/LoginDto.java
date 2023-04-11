package com.hotel.GoldenChariot.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LoginDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String role;

    public LoginDto(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
