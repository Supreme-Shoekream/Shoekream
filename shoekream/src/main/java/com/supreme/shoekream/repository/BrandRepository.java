package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Admin, Long> {
}
