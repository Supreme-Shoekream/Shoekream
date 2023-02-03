package com.supreme.admin.service;

import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountApiLogicService {
    private final MemberRepository memberRepository;
    @Transactional
    public MemberDTO list(Long idx){
        Member member = memberRepository.findByIdx(idx);
        MemberDTO memberDTO = MemberDTO.fromEntity(member);
        return memberDTO;
    }
    @Transactional
    public void update(MemberDTO dto){
        try {
            Member member = memberRepository.getReferenceByIdx(dto.idx());
            if(dto.accNumber() != null) {member.setAccNumber(dto.accNumber());}
        } catch (EntityNotFoundException e){

        }
    }
    @Transactional
    public void delete(Long idx){
        memberRepository.deleteByIdx(idx);
    }
}
