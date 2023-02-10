package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByHp(String Hp);
    Optional<Member> findByEmailAndMemberPw(String email, String memberPw);
    Member findByIdx(Long idx);
    Optional<Member> deleteByIdx(Long idx);
    Member getReferenceByIdx(Long idx);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.memberPw = ?2 WHERE m.email = ?1")
    int updateMemberPw(String email, String pw);

}
