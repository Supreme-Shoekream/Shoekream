package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByMemberIdx(Long idx);
    List<Reply> deleteByMemberIdx(Long idx);
}
