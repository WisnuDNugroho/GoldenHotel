package com.hotel.GoldenChariot.service.admin;

import com.hotel.GoldenChariot.dao.AdminRepository;
import com.hotel.GoldenChariot.dto.admin.AdminDto;
import com.hotel.GoldenChariot.dto.admin.AdminInsertDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginDto getDataAdmin(String username) {
        return adminRepository.getDataAdmin(username);
    }

    @Override
    public List<AdminDto> getListAdmin(Integer page) {
        Integer getRowsPage = 3;
        Pageable pagination = PageRequest.of(page-1, getRowsPage);
        return adminRepository.getListAdmin(pagination);
    }

    @Override
    public Long getCountPage() {
        var rowPerPage = 3;
        double totalData = (double) adminRepository.getCount();
        long totalPage = (long) (Math.ceil(totalData / rowPerPage));
        var temp = adminRepository.findAll();
        return totalPage;
    }

    @Override
    public void save(AdminInsertDto dto) {
        Admin admin = new Admin(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), "Admin", dto.getJobTitle());
        adminRepository.save(admin);
    }

    @Override
    public void delete(String username) {
        adminRepository.deleteById(username);
    }
}
