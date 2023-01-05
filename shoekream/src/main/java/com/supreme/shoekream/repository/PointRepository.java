package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
