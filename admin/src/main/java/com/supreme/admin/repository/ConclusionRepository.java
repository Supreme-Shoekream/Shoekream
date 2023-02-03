package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Conclusion;
import com.supreme.admin.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConclusionRepository extends JpaRepository<Conclusion, Long> {
    List<Conclusion> findByProductIdx(Long idx);
    List<Conclusion> findAllByProduct(Product product);
}