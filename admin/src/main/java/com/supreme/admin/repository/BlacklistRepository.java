package com.supreme.admin.repository;


import com.supreme.admin.model.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlacklistRepository extends JpaRepository<Blacklist,Long> {
    List<Blacklist> deleteByMemberIdx(Long idx);
}
