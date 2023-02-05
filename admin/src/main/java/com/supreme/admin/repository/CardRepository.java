package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByIdx(Long idx);
    List<Card> findByMemberIdx(Long idx);
    Optional<Card> getReferenceByIdx(Long idx);
    List<Card> findByMemberIdxAndCardBasic(Long idx, boolean isBasic);
}
