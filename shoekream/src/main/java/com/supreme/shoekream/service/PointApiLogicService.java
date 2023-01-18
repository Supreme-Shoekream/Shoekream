package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.enumclass.PointType;
import com.supreme.shoekream.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Transactional
    public Point create(Point request){
        switch (request.getReason()){

        }
        Point point = Point.builder().point(request.getPoint()).reason(PointType.EVENT_WINNINGS)
                .memberIdx(request.getMemberIdx()).build();
        pointRepository.save(point);
        return point;
    }

    @Transactional
    public Optional<Point> list(Long userIdx){
        return pointRepository.findByMemberIdx(userIdx);
    }

    @Transactional
    public String update(Long userIdx){
        Optional<Member> member = memberRepository.findById(userIdx);
        member.ifPresent(
                memberUpdate -> {
                    memberUpdate.setPoint(memberUpdate.getPoint()+100);
                    memberRepository.save(memberUpdate);
                }
        );
        return "";
    }
}
