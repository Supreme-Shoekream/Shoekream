package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Follow;
import com.supreme.admin.model.entity.Lk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollowingIdx(Long idx);
    List<Follow> findAllByFollowerIdx(Long idx);

    Optional<Follow> findByFollowerIdxAndFollowingIdx(Long followerIdx, Long followingIdx);
}
