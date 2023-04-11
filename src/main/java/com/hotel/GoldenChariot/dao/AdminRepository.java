package com.hotel.GoldenChariot.dao;

import com.hotel.GoldenChariot.dto.admin.AdminDto;
import com.hotel.GoldenChariot.dto.login.LoginDto;
import com.hotel.GoldenChariot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.login.LoginDto(adm.username, adm.password, adm.role)
            FROM Admin AS adm
            WHERE adm.username = :username
            """)
    public LoginDto getDataAdmin(@Param("username") String username);

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.admin.AdminDto(adm.username, adm.jobTitle)
            FROM Admin AS adm
            WHERE adm.role = 'Admin'
            """)
    public List<AdminDto> getListAdmin(Pageable pageable);

    @Query("""
            SELECT COUNT(adm.username)
            FROM Admin AS adm
            WHERE adm.role = 'Admin'
            """)
    public Long getCount();
}
