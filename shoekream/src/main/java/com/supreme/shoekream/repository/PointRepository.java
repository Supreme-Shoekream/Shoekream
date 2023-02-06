package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByMemberIdx(Long idx);
    Point findByIdx(Long idx);
    List<Point> deleteByMemberIdx(Long idx);
}