package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Penalty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty,Long> {
    Page<Penalty> findBySell_Member_Email(String searchKeyword, Pageable pageable);
}
