package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.dto.EventMemberDTO;
import com.supreme.shoekream.model.entity.EventMember;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.EventMemberRepository;
import com.supreme.shoekream.repository.EventRepository;
import com.supreme.shoekream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class EventMemberService {
    final EventMemberRepository eventMemberRepository;
    final MemberRepository memberRepository;
    final EventRepository eventRepository;
    @Transactional
    public Header<EventMemberDTO> create(EventMemberDTO dto,Long memberIdx){
        EventProduct eventProduct =eventRepository.findById(dto.eventProductIdx()).get();
        Member member = memberRepository.findById(memberIdx).get();
        System.out.println("+++"+ !eventMemberRepository.existsByEventProductAndMember(eventProduct,member));
        if(!eventMemberRepository.existsByEventProductAndMember(eventProduct,member)){
            EventMember draw = eventMemberRepository.save(dto.toEntity(eventProduct,member));
            return Header.OK(EventMemberDTO.fromEntity(draw));
        }else{
            return Header.ERROR("이미 응모하셨습니다.");
        }


    }
}
