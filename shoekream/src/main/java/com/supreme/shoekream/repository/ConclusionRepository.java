package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConclusionRepository extends JpaRepository<Conclusion, Long> {
    List<Conclusion> findByProductIdx(Long idx);
    List<Conclusion> findAllByProduct(Product product);

    List<Conclusion> findAllByProductOrderByCreatedAtDesc(Product product);
    Conclusion findTop1ByProductIdxOrderByCreatedAtDesc(Long idx);
    long countByProductAndCreatedAtAfter(Product product, LocalDateTime localDateTime);
}