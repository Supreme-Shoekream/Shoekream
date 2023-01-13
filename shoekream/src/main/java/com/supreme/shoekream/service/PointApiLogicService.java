package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PointApiLogicService {
    private final PointRepository pointRepository;

    @Transactional
    public Point create(Point request){
        Point point = Point.builder().point(request.getPoint()).reason(request.getReason())
                .memberIdx(1L).build();
        pointRepository.save(point);
        return point;
    }

    @Transactional
    public Optional<Point> list(){
        return pointRepository.findByMemberIdx(1L);
    }
}
