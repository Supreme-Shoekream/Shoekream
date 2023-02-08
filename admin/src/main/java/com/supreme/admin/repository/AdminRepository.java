package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    Optional<Admin> findByAdminid(String adminid);
    Optional<Admin> findByAdminidAndAdminpw(String adminid, String adminpw);
    Page<Admin> findByName(String keyword, Pageable pageable);
}
