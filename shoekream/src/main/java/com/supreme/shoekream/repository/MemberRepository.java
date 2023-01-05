package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberIdAndMemberPw(String memberId, String memberPw);
}
