package com.supreme.admin.repository;

import com.supreme.admin.model.dto.EventMemberDTO;
import com.supreme.admin.model.entity.EventMember;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventMemberRepository extends JpaRepository<EventMember, Long> {
    Boolean existsByEventProductAndMember(EventProduct eventProduct, Member member);

    @Query(value = "SELECT * FROM event_member order by RAND() limit 1",nativeQuery = true)
    List<EventMember> findAll();
}
