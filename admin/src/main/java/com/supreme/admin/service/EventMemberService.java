package com.supreme.admin.service;

import com.supreme.admin.model.dto.EventMemberDTO;
import com.supreme.admin.model.entity.EventMember;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.repository.EventMemberRepository;
import com.supreme.admin.repository.EventRepository;
import com.supreme.admin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<EventMember> list(){
        return  eventMemberRepository.findAll();
    }
}
