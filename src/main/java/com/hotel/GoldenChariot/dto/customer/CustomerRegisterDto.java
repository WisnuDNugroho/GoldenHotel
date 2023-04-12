package com.hotel.GoldenChariot.dto.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRegisterDto {

  @NotBlank(message = "Username required!")
  @Size(min = 5, max = 15, message = "Username at least 5 character and maximum 15 character")
  private String username;

  @NotBlank(message = "Password required!")
  @Size(min = 5, max = 30, message = "Password at least 5 character and maximum 30 character")
  private String password;

  @NotBlank(message = "Fullname required!")
  @Size(max = 200, message = "Fullname maximum 200 character")
  private String fullname;

  @NotBlank(message = "E-mail required!")
  @Size(max = 200, message = "E-mail maximum 200 character")
  private String email;

  @NotBlank(message = "Address required!")
  @Size(max = 200, message = "Address maximum 200 character")
  private String address;
  
}
