package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    Optional<Admin> findByAdminid(String adminid);
    Optional<Admin> findByAdminidAndAdminpw(String adminid, String adminpw);
}
