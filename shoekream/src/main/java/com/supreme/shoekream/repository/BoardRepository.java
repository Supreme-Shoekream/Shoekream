package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Sell;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByMemberIdx(Long memberIdx);
    Board findByIdx(Long idx);

    int countAllByMemberIdx(Long memberIdx);
    List<Board> findAllByHashtag(String hashtag);
    List<Board> deleteByMemberIdx(Long idx);

}
