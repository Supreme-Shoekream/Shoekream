package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.PointApiResponse;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class PointApiLogicService {
    private final PointRepository pointRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Header<PointApiResponse> create(PointDTO dto, Long idx){
        Member member = memberRepository.getReferenceByIdx(idx);
        pointRepository.save(dto.toEntity(member));
        return Header.OK();
    }

    @Transactional
    public List<Point> list(Long idx){
        return pointRepository.findByMemberIdx(idx);
    }

    public List<Point> listAll(){
        return pointRepository.findAll(Sort.by(Sort.Direction.DESC,"idx"));
    }


    @Transactional
    public Header<MemberDTO> update(PointDTO dto, Long idx){
        Member member = memberRepository.findByIdx(idx);
        Long point;
        if(member.getPoint() == null){
            point = 0L;
        }else {
            point = member.getPoint();
        }
        member.setPoint(point+dto.point());
        return Header.OK();
    }
}
