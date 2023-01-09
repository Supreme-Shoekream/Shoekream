package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Long> findAllByfollowerIdx(Long followerIdx);
}
