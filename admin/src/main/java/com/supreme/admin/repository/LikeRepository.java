package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Board;
import com.supreme.admin.model.entity.Lk;
import com.supreme.admin.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Lk, Long> {
    List<Lk> findAllByBoardIdx(Long boardIdx);
    List<Lk> findAllByMember(Member member);
    Lk findByBoardAndMember(Board board, Member member);
}
