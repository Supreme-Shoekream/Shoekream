package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventProduct, Long> {
    Optional<EventProduct> findByIdx(Long idx);

    Optional<EventProduct> findFirstByOrderByIdxDesc();
}
