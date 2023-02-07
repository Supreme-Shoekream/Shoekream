package com.supreme.admin.repository;

import com.supreme.admin.model.entity.EventMember;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventMemberRepository extends JpaRepository<EventMember, Long> {
    Boolean existsByEventProductAndMember(EventProduct eventProduct, Member member);
}
