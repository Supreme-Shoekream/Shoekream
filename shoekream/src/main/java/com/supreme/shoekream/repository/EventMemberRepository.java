package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.EventMember;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventMemberRepository extends JpaRepository<EventMember, Long> {
    Boolean existsByEventProductAndMember(EventProduct eventProduct, Member member);
}
