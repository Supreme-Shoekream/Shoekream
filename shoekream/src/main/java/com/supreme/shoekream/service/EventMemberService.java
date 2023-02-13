package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.dto.EventMemberDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.EventMember;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.enumclass.PointType;
import com.supreme.shoekream.model.enumclass.Reason;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.EventMemberRepository;
import com.supreme.shoekream.repository.EventRepository;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class EventMemberService {
    final EventMemberRepository eventMemberRepository;
    final MemberRepository memberRepository;
    final EventRepository eventRepository;
    final PointRepository pointRepository;
    @Transactional
    public Header<EventMemberDTO> create(EventMemberDTO dto,Long memberIdx){
        EventProduct eventProduct =eventRepository.findById(dto.eventProductIdx()).get();
        Member member = memberRepository.findById(memberIdx).get();
        System.out.println("+++"+ !eventMemberRepository.existsByEventProductAndMember(eventProduct,member));
        if(!eventMemberRepository.existsByEventProductAndMember(eventProduct,member) && (member.getPoint()>=1000)){
            EventMember draw = eventMemberRepository.save(dto.toEntity(eventProduct,member));
            member.setPoint(member.getPoint()-1000);
            PointDTO point = PointDTO.of((long) -1000, PointType.EVENT_USE, LocalDateTime.now());
            pointRepository.save(point.toEntity(member));
            return Header.OK(EventMemberDTO.fromEntity(draw));
        }else{
            return Header.ERROR("이미 응모하셨습니다.");
        }


    }
}
