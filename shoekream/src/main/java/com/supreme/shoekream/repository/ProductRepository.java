package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Admin;
import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdx(Long idx);
}
