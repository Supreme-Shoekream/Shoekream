package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByMember(Member member);

    Optional<Wish> findByMemberIdxAndProductIdx(Long memberIdx, Long ProductIdx);

    List<Wish> deleteByMemberIdx(Long idx);
    
    Boolean existsByMemberAndProduct(Member member, Product product);
    Long countByProduct(Product product);
}
