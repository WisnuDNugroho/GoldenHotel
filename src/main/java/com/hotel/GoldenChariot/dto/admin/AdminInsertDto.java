package com.hotel.GoldenChariot.dto.admin;

import com.hotel.GoldenChariot.validator.account.ConfirmPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ConfirmPassword(password = "password", confirmPassword = "passwordConfirm", message = "Tolong periksa kembali password anda")
public class AdminInsertDto {
    private String username;
    private String jobTitle;
    private String password;

    @NotBlank(message = "Confirm password tidak boleh kosong")
    private String passwordConfirm;
}
