package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByMember(Member member);

    Optional<Wish> findByMemberIdxAndProductIdx(Long memberIdx, Long ProductIdx);

}
