package com.supreme.admin.service;

import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.dto.PointDTO;
import com.supreme.admin.model.entity.Admin;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.entity.Point;
import com.supreme.admin.model.enumclass.PointType;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.response.AdminApiResponse;
import com.supreme.admin.model.network.response.PointApiResponse;
import com.supreme.admin.repository.AdminRepository;
import com.supreme.admin.repository.MemberRepository;
import com.supreme.admin.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Page<Point> listAll(Pageable pageable){
        return pointRepository.findAll(pageable);
    }

    @Transactional
    public Header<MemberDTO> update(PointDTO dto, Long idx){
        Member member = memberRepository.findByIdx(idx);
        Long point = member.getPoint();
        member.setPoint(point+dto.point());
        return Header.OK();
    }
}
