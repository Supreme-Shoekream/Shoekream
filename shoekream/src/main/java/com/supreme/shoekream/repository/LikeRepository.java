package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Lk;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Lk, Long> {
    List<Lk> findAllByBoardIdx(Long boardIdx);
    List<Lk> findAllByMember(Member member);
    Lk findByBoardAndMember(Board board, Member member);
    List<Lk> deleteByMemberIdx(Long idx);
}
