package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Blacklist;
import com.supreme.shoekream.model.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlacklistRepository extends JpaRepository<Blacklist,Long> {
    List<Blacklist> deleteByMemberIdx(Long idx);
}
