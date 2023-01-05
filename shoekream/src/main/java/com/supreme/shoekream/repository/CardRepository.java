package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
