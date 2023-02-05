package com.supreme.admin.repository;

import com.supreme.admin.model.dto.ConclusionDTO;
import com.supreme.admin.model.entity.Buy;
import com.supreme.admin.model.entity.Conclusion;
import com.supreme.admin.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConclusionRepository extends JpaRepository<Conclusion, Long> {
    List<Conclusion> findByProductIdx(Long idx);
    List<Conclusion> findAllByProduct(Product product);
    long countBy();
    long countByProductAndCreatedAtAfter(Product product, LocalDateTime localDateTime);
    List<Conclusion> findByProductAndCreatedAtAfter(Product product, LocalDateTime localDateTime);
    List<Conclusion> findByCreatedAtAfterAndCreatedAtBefore(LocalDateTime start,LocalDateTime end);

    Page<Conclusion> findByProduct_ModelNumContainingOrProduct_NameContaining(String modelNum, String ProductName, Pageable pageable);
}