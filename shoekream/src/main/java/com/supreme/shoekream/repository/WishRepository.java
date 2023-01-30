package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
//    List<Wish> findAllByProduct(Product product);
    List<Wish> findByMember(Member member);

    Optional<Wish> findByProductIdx(Long productIdx);

}
