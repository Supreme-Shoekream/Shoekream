package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
