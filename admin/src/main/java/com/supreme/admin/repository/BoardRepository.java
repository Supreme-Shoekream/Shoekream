package com.supreme.admin.repository;

import com.supreme.admin.model.dto.socialDTO.BoardDTO;
import com.supreme.admin.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByMemberIdx(Long memberIdx);
    Board findByIdx(Long idx);

    int countAllByMemberIdx(Long memberIdx);

    List<Board> findAllByHashtag(String hashtag);
    long countBy();
}
