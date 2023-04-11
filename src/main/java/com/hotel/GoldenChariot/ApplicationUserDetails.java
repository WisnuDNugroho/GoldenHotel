package com.hotel.GoldenChariot;

import com.hotel.GoldenChariot.dto.login.LoginDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ApplicationUserDetails implements UserDetails {

    private String username;
    private String password;
    private String role;
    private List<GrantedAuthority> authorityList = new ArrayList<>();

    public ApplicationUserDetails(LoginDto dto){
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.role = dto.getRole();
        this.authorityList.add(new SimpleGrantedAuthority(dto.getRole()));
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
