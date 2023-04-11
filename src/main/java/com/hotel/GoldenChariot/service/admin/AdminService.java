package com.hotel.GoldenChariot.service.admin;

import com.hotel.GoldenChariot.dao.AdminRepository;
import com.hotel.GoldenChariot.dto.admin.AdminDto;
import com.hotel.GoldenChariot.dto.admin.AdminInsertDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface AdminService {
    public LoginDto getDataAdmin(String username);
    public List<AdminDto> getListAdmin(Integer page);
    public Long getCountPage();
    public void save(AdminInsertDto dto);
    public void delete(String username);
}
