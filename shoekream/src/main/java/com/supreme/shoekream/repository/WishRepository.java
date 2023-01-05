package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
