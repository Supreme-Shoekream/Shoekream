package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByMemberIdx(Long idx);
    Point findByIdx(Long idx);
}