package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Address;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventProduct, Long> {
    Optional<EventProduct> findByIdx(Long idx);


}
