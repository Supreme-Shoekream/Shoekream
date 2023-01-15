package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConclusionRepository extends JpaRepository<Conclusion, Long> {
    List<Conclusion> findByProduct(Product product);
}